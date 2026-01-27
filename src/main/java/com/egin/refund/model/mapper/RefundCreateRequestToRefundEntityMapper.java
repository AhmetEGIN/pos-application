package com.egin.refund.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.order.model.Order;
import com.egin.order.model.mapper.OrderToOrderEntityMapper;
import com.egin.refund.model.dto.request.RefundCreateRequest;
import com.egin.refund.model.entity.RefundEntity;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RefundCreateRequestToRefundEntityMapper {


    public RefundEntity toRefundEntity(
            final RefundCreateRequest request,
            final Order order,
            final Branch branch,
            final User cashier
    ) {

        return RefundEntity.builder()
                .orderEntity(OrderToOrderEntityMapper.toOrderEntity(order))
                .reason(request.getReason())
                .amount(request.getAmount())
                .paymentType(request.getPaymentType())
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .cashier(UserToUserEntityMapper.toUserEntity(cashier))
                .build();
    }
}
