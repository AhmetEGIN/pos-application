package com.egin.order.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.entity.OrderEntity;
import com.egin.order.model.enums.OrderStatus;
import com.egin.user.model.Customer;
import com.egin.user.model.User;
import com.egin.user.model.mapper.CustomerToCustomerEntityMapper;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

@UtilityClass
public class OrderCreateRequestToOrderEntityMapper {


    public OrderEntity toOrderEntity(
            final OrderCreateRequest request,
            final Branch branch,
            final User cashier,
            final Customer customer
    ) {
        return OrderEntity.builder()
                .paymentType(request.getPaymentType())
                .orderStatus(OrderStatus.PENDING)
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .cashier(UserToUserEntityMapper.toUserEntity(cashier))
                .customerEntity(CustomerToCustomerEntityMapper.toUserEntity(customer))
                .orderItems(new ArrayList<>())
                .build();
    }

}
