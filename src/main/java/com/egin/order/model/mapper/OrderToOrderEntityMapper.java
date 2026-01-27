package com.egin.order.model.mapper;

import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.order.model.Order;
import com.egin.order.model.entity.OrderEntity;
import com.egin.user.model.mapper.CustomerToCustomerEntityMapper;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderToOrderEntityMapper {


    public OrderEntity toOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .paymentType(order.getPaymentType())
                .totalAmount(order.getTotalAmount())
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(order.getBranch()))
                .cashier(UserToUserEntityMapper.toUserEntity(order.getCashier()))
                .customerEntity(CustomerToCustomerEntityMapper.toUserEntity(order.getCustomer()))
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
