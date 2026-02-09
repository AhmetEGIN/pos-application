package com.egin.order.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.order.model.Order;
import com.egin.order.model.entity.OrderEntity;
import com.egin.user.model.mapper.CustomerEntityToCustomerMapper;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderEntityToOrderMapper {

    public Order toOrder(final OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        return Order.builder()
                .id(orderEntity.getId())
                .totalAmount(orderEntity.getTotalAmount())
                .paymentType(orderEntity.getPaymentType())
                .orderStatus(orderEntity.getOrderStatus())
                .branch(BranchEntityToBranchMapper.toBranch(orderEntity.getBranchEntity()))
                .cashier(UserEntityToUserMapper.toUser(orderEntity.getCashier()))
                .customer(CustomerEntityToCustomerMapper.toCustomer(orderEntity.getCustomerEntity()))
                .orderItems(ListOrderItemEntityToListOrderItemMapper.toOrderItemList(orderEntity.getOrderItems()))
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .createdBy(orderEntity.getCreatedBy())
                .updatedBy(orderEntity.getUpdatedBy())
                .build();
    }

}

