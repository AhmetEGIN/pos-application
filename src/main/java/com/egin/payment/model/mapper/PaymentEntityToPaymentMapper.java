package com.egin.payment.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.order.model.mapper.OrderEntityToOrderMapper;
import com.egin.payment.model.Payment;
import com.egin.payment.model.entity.PaymentEntity;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentEntityToPaymentMapper {
    public Payment toPayment(final PaymentEntity paymentEntity) {
        return Payment.builder()
                .id(paymentEntity.getId())
                .idempotencyKey(paymentEntity.getIdempotencyKey())
                .amount(paymentEntity.getAmount())
                .currency(paymentEntity.getCurrency())
                .status(paymentEntity.getPaymentStatus())
                .paymentType(paymentEntity.getPaymentType())
                .order(paymentEntity.getOrderEntity() != null
                        ? OrderEntityToOrderMapper.toOrder(paymentEntity.getOrderEntity())
                        : null)
                .cashier(paymentEntity.getCashier() != null
                        ? UserEntityToUserMapper.toUser(paymentEntity.getCashier())
                        : null)
                .branch(paymentEntity.getBranchEntity() != null
                        ? BranchEntityToBranchMapper.toBranch(paymentEntity.getBranchEntity())
                        : null)
                .externalReferenceId(paymentEntity.getExternalReferenceId())
                .errorMessage(paymentEntity.getErrorMessage())
                .createdAt(paymentEntity.getCreatedAt())
                .updatedAt(paymentEntity.getUpdatedAt())
                .createdBy(paymentEntity.getCreatedBy())
                .updatedBy(paymentEntity.getUpdatedBy())
                .build();
    }
}
