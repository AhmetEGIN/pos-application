package com.egin.user.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.user.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Customer> toCustomPagingResponse(
            CustomPage<Customer> customerPage
    ) {
        if (customerPage == null) {
            return null;
        }

        return CustomPagingResponse.<Customer>builder()
                .content(customerPage.getContent())
                .totalElementCount(customerPage.getTotalElementCount())
                .totalPageCount(customerPage.getTotalPageCount())
                .pageNumber(customerPage.getPageNumber())
                .pageSize(customerPage.getPageSize())
                .build();
    }
}

