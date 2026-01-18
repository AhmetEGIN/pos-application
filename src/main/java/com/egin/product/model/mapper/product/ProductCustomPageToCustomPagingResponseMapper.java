package com.egin.product.model.mapper.product;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.product.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Product> toCustomPagingResponse(
            CustomPage<Product> productPage
    ) {
        if (productPage == null) {
            return null;
        }

        return CustomPagingResponse.<Product>builder()
                .content(productPage.getContent())
                .totalElementCount(productPage.getTotalElementCount())
                .totalPageCount(productPage.getTotalPageCount())
                .pageNumber(productPage.getPageNumber())
                .pageSize(productPage.getPageSize())
                .build();
    }

}
