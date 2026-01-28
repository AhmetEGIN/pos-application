package com.egin.shiftReport.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.shiftReport.model.ShiftReport;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShiftReportCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<ShiftReport> toCustomPagingResponse(final CustomPage<ShiftReport> shiftReportPage) {
        if (shiftReportPage == null) {
            return null;
        }

        return CustomPagingResponse.<ShiftReport>builder()
                .content(shiftReportPage.getContent())
                .pageNumber(shiftReportPage.getPageNumber())
                .pageSize(shiftReportPage.getPageSize())
                .totalElementCount(shiftReportPage.getTotalElementCount())
                .totalPageCount(shiftReportPage.getTotalPageCount())
                .build();
    }

}

