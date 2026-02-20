package com.egin.payment.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.order.model.Order;
import com.egin.order.model.enums.OrderStatus;
import com.egin.order.service.OrderService;
import com.egin.payment.exception.IllegalPaymentStateTransitionException;
import com.egin.payment.exception.PaymentAlreadyProcessedException;
import com.egin.payment.exception.PaymentNotFoundException;
import com.egin.payment.exception.PaymentProcessingException;
import com.egin.payment.model.Payment;
import com.egin.payment.model.dto.request.PaymentByOrderPagingRequest;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.dto.request.PaymentRefundRequest;
import com.egin.payment.model.entity.PaymentEntity;
import com.egin.payment.model.enums.PaymentStatus;
import com.egin.payment.model.mapper.ListPaymentEntityToPaymentMapper;
import com.egin.payment.model.mapper.PaymentCreateRequestToPaymentEntityMapper;
import com.egin.payment.model.mapper.PaymentEntityToPaymentMapper;
import com.egin.payment.repository.PaymentRepository;
import com.egin.payment.service.PaymentService;
import com.egin.payment.service.PaymentStateMachine;
import com.egin.payment.service.strategy.PaymentResult;
import com.egin.payment.service.strategy.PaymentStrategy;
import com.egin.payment.service.strategy.PaymentStrategyFactory;
import com.egin.user.model.User;
import com.egin.user.service.user.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final PaymentRepository paymentRepository;
    private final PaymentStateMachine paymentStateMachine;
    private final PaymentStrategyFactory paymentStrategyFactory;
    private final OrderService orderService;
    private final BranchService branchService;
    private final UserReadService userReadService;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            PaymentStateMachine paymentStateMachine,
            PaymentStrategyFactory paymentStrategyFactory,
            OrderService orderService,
            BranchService branchService,
            UserReadService userReadService
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentStateMachine = paymentStateMachine;
        this.paymentStrategyFactory = paymentStrategyFactory;
        this.orderService = orderService;
        this.branchService = branchService;
        this.userReadService = userReadService;
    }


    @Override
    @Transactional
    public Payment processPayment(String idempotencyKey, PaymentCreateRequest request) {

        Optional<PaymentEntity> existingPayment = paymentRepository
                .findByIdempotencyKeyForUpdate(idempotencyKey);

        // 1. Idempotency kontrolü
        if (existingPayment.isPresent()) {
            PaymentEntity paymentEntity = existingPayment.get();

            log.info("Existing payment found for idempotency key: {}, paymentId: {}, status: {}",
                    idempotencyKey, paymentEntity.getId(), paymentEntity.getPaymentStatus());

            // Zaten ödeme işlemi tamamlanmışsa, mevcut ödeme bilgisini döndür
            if (paymentEntity.getPaymentStatus() == PaymentStatus.PAID) {

                return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
            }

            // Ödeme işlemi devam ediyorsa, mevcut durumu döndür
            if (paymentEntity.getPaymentStatus() == PaymentStatus.PROCESSING) {
                throw new PaymentProcessingException("Payment is already being processed for idempotency key: " + idempotencyKey);
            }

            if (paymentEntity.getPaymentStatus() == PaymentStatus.FAILED) {
                return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
            }

            throw new PaymentAlreadyProcessedException("Payment is already processed for idempotency key: " + idempotencyKey);

        }

        // 2. Order ve branch bilgilerini al
        Order order = orderService.getOrderById(request.getOrderId());
        Branch branch = branchService.getBranchById(request.getBranchId());
        User cashier = userReadService.getCurrentUser();

        // 3. Order için zaten başarılı ödeme var mı kontrolü
        if (paymentRepository.existsPaidPaymentByOrderId(request.getOrderId())) {
            throw new PaymentAlreadyProcessedException("A successful payment already exists for order id: " + request.getOrderId());
        }

        // 4. Yeni ödeme kaydı oluştur

        PaymentEntity newPaymentEntity = PaymentCreateRequestToPaymentEntityMapper.toPaymentEntity(idempotencyKey, request, order, branch, cashier);
        newPaymentEntity.setPaymentStatus(PaymentStatus.CREATED);
        newPaymentEntity = paymentRepository.save(newPaymentEntity);

        log.info("New payment created with id: {}, for order id: {}, by cashier id: {}",
                newPaymentEntity.getId(), request.getOrderId(), cashier.getId());

        // 5. State transition: CREATED → PROCESSING
        newPaymentEntity = transitionTo(newPaymentEntity, PaymentStatus.PROCESSING);

        try {
            // 6. ödeme türüne göre strategy seç ve işlemi gerçekleştir
            PaymentStrategy paymentStrategy = paymentStrategyFactory.getStrategy(request.getPaymentType());

            PaymentResult paymentResult = paymentStrategy.processPayment(newPaymentEntity, request);

            if (paymentResult.isSuccess()) {
                // 7. başarılı ise : PROCESSING → PAID
                newPaymentEntity = transitionTo(newPaymentEntity, PaymentStatus.PAID);
                newPaymentEntity.setExternalReferenceId(paymentResult.getExternalReferenceId());

                updateOrderStatus(request.getOrderId(), OrderStatus.COMPLETED);

                log.info("Payment successful for payment id: {}, order id: {}", newPaymentEntity.getId(), request.getOrderId());

            } else {
                newPaymentEntity = transitionTo(newPaymentEntity, PaymentStatus.FAILED);
                newPaymentEntity.setErrorMessage(paymentResult.getErrorCode() + ": " + paymentResult.getErrorMessage());
                log.warn("Payment failed for payment id: {}, order id: {}, reason: {}", newPaymentEntity.getId(), request.getOrderId(), paymentResult.getErrorMessage());

            }
            newPaymentEntity = paymentRepository.save(newPaymentEntity);

        } catch (Exception e) {
            log.error("Payment processing error: {}", e.getMessage(), e);

            newPaymentEntity.setPaymentStatus(PaymentStatus.FAILED);
            newPaymentEntity.setErrorMessage(e.getMessage());
            paymentRepository.save(newPaymentEntity);

            throw new PaymentProcessingException("Error processing payment: " + e);
        }

        return PaymentEntityToPaymentMapper.toPayment(newPaymentEntity);
    }

    @Override
    public Payment getPaymentById(final String paymentId) {

        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));


        return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
    }

    @Override
    public Payment getPaymentByIdempotencyKey(final String idempotencyKey) {

        PaymentEntity paymentEntity = paymentRepository.findByIdempotencyKey(idempotencyKey)
                .orElseThrow(() -> new PaymentNotFoundException("idempotencyKey: " + idempotencyKey));

        return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
    }

    @Override
    public CustomPage<Payment> getPaymentsByOrderId(final String orderId, final PaymentByOrderPagingRequest request) {

        Page<PaymentEntity> paymentPage = paymentRepository.findByOrderEntityId(orderId, request.toPageable());

        List<Payment> payments = ListPaymentEntityToPaymentMapper
                .toPaymentList(paymentPage.getContent());


        return CustomPage.of(payments, paymentPage);
    }

    @Override
    public Payment cancelPayment(final String paymentId) {

        log.info("Canceling payment: {}", paymentId);

        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("id" + paymentId));

        // Sadece PAID durumundaki ödemeler iptal edilebilir
        if (paymentEntity.getPaymentStatus() != PaymentStatus.PAID) {
            throw new IllegalPaymentStateTransitionException(
                    "Only PAID payments can be canceled. Current status: " + paymentEntity.getPaymentStatus());
        }

        // State transition: PAID → CANCELED
        paymentEntity = transitionTo(paymentEntity, PaymentStatus.CANCELED);
        paymentEntity = paymentRepository.save(paymentEntity);

        // Order durumunu güncelle
        if (paymentEntity.getOrderEntity() != null) {
            updateOrderStatus(
                    paymentEntity.getOrderEntity().getId(),
                    OrderStatus.CANCELLED
            );
        }

        log.info("Payment canceled: {}", paymentId);

        return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
    }

    @Override
    @Transactional
    public Payment refundPayment(final String paymentId, final PaymentRefundRequest request) {

        log.info("Refunding payment: {}, reason: {}", paymentId, request.getReason());

        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("id" + paymentId));

        // Sadece PAID durumundaki ödemeler iade edilebilir
        if (paymentEntity.getPaymentStatus() != PaymentStatus.PAID) {
            throw new IllegalPaymentStateTransitionException(
                    "Only PAID payments can be refunded. Current status: " + paymentEntity.getPaymentStatus());
        }

        // Ödeme türüne göre strateji seç ve iade işlemini gerçekleştir
        PaymentStrategy strategy = paymentStrategyFactory.getStrategy(paymentEntity.getPaymentType());
        PaymentResult result = strategy.processRefund(paymentEntity);

        if (!result.isSuccess()) {
            throw new PaymentProcessingException("Refund processing failed: " + result.getErrorMessage());
        }

        // State transition: PAID → REFUNDED
        paymentEntity = transitionTo(paymentEntity, PaymentStatus.REFUNDED);
        paymentEntity = paymentRepository.save(paymentEntity);

        // Order durumunu güncelle
        if (paymentEntity.getOrderEntity() != null) {
            updateOrderStatus(paymentEntity.getOrderEntity().getId(), OrderStatus.REFUNDED);
        }

        log.info("Payment refunded: {}", paymentId);

        return PaymentEntityToPaymentMapper.toPayment(paymentEntity);
    }



    @Override
    public boolean hasSuccessfulPayment(final String orderId) {
        return paymentRepository.existsPaidPaymentByOrderId(orderId);
    }


    private PaymentEntity transitionTo(final PaymentEntity payment, final PaymentStatus newStatus) {
        PaymentStatus currentStatus = payment.getPaymentStatus();
        paymentStateMachine.validateTransition(currentStatus, newStatus);

        payment.setPaymentStatus(newStatus);
        log.debug("Payment {} transitioned from {} to {}",
                payment.getId(), currentStatus, newStatus);

        return paymentRepository.save(payment);
    }


    private void updateOrderStatus(final String id, final OrderStatus orderStatus) {

        Order order = orderService.getOrderById(id);

        if (order != null){
//            orderService.updateOrderStatus(id, orderStatus);
        }

    }

}
