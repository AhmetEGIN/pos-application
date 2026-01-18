package com.egin.product.model.mapper.category;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.product.model.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Category> toCustomPagingResponse(
            final CustomPage<Category> categoryPage
    ) {
        if (categoryPage == null) {
            return null;
        }

        return CustomPagingResponse.<Category>builder()
                .content(categoryPage.getContent())
                .totalElementCount(categoryPage.getTotalElementCount())
                .totalPageCount(categoryPage.getTotalPageCount())
                .pageNumber(categoryPage.getPageNumber())
                .pageSize(categoryPage.getPageSize())
                .build();


    }


}
