package com.egin.order.model.mapper;

import com.egin.order.model.OrderItem;
import com.egin.order.model.entity.OrderItemEntity;
import com.egin.product.model.mapper.product.ProductEntityToProductMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderItemEntityToOrderItemMapper {

    public OrderItem toOrderItem(final OrderItemEntity orderItemEntity) {
        if (orderItemEntity == null) {
            return null;
        }

        return OrderItem.builder()
                .id(orderItemEntity.getId())
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .product(ProductEntityToProductMapper.toProduct(orderItemEntity.getProductEntity()))
                .build();
    }

}

