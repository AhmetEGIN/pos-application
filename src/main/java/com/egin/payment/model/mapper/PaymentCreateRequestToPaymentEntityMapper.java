package com.egin.payment.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.order.model.Order;
import com.egin.order.model.mapper.OrderToOrderEntityMapper;
import com.egin.payment.model.dto.request.PaymentCreateRequest;
import com.egin.payment.model.entity.PaymentEntity;
import com.egin.payment.model.enums.PaymentStatus;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentCreateRequestToPaymentEntityMapper {


    public static PaymentEntity toPaymentEntity(
            final String idempotencyKey,
            final PaymentCreateRequest request,
            final Order order,
            final Branch branch,
            final User cashier) {

        return PaymentEntity.builder()
            .idempotencyKey(idempotencyKey)
            .amount(request.getAmount())
            .currency(request.getCurrency())
            .paymentStatus(PaymentStatus.CREATED)
            .paymentType(request.getPaymentType())
            .orderEntity(OrderToOrderEntityMapper.toOrderEntity(order))
            .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
            .cashier(UserToUserEntityMapper.toUserEntity(cashier))
            .build();
    }
}
