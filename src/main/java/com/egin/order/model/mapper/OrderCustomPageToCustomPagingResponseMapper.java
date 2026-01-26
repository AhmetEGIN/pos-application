package com.egin.order.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.order.model.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Order> toCustomPagingResponse(final CustomPage<Order> orderPage) {
        if (orderPage == null) {
            return null;
        }

        return CustomPagingResponse.<Order>builder()
                .content(orderPage.getContent())
                .pageNumber(orderPage.getPageNumber())
                .pageSize(orderPage.getPageSize())
                .totalElementCount(orderPage.getTotalElementCount())
                .totalPageCount(orderPage.getTotalPageCount())
                .build();
    }

}

