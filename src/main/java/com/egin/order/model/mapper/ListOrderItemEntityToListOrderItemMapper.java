package com.egin.order.model.mapper;

import com.egin.order.model.OrderItem;
import com.egin.order.model.entity.OrderItemEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListOrderItemEntityToListOrderItemMapper {

    public List<OrderItem> toOrderItemList(final List<OrderItemEntity> orderItemEntities) {
        if (orderItemEntities == null) {
            return null;
        }

        return orderItemEntities.stream()
                .map(OrderItemEntityToOrderItemMapper::toOrderItem)
                .collect(Collectors.toList());
    }

}
