package com.egin.order.model.mapper;

import com.egin.order.model.Order;
import com.egin.order.model.entity.OrderEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListOrderEntityToListOrderMapper {

    public List<Order> toOrderList(final List<OrderEntity> orderEntities) {
        if (orderEntities == null) {
            return null;
        }

        return orderEntities.stream()
                .map(OrderEntityToOrderMapper::toOrder)
                .collect(Collectors.toList());
    }

}
