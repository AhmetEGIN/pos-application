package com.egin.refund.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.refund.model.Refund;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RefundCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Refund> toCustomPagingResponse(final CustomPage<Refund> refundPage) {
        if (refundPage == null) {
            return null;
        }

        return CustomPagingResponse.<Refund>builder()
                .content(refundPage.getContent())
                .pageNumber(refundPage.getPageNumber())
                .pageSize(refundPage.getPageSize())
                .totalElementCount(refundPage.getTotalElementCount())
                .totalPageCount(refundPage.getTotalPageCount())
                .build();
    }

}
