package com.egin.branch.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchCustomPageToCustomPagingResponseMapper {

    public CustomPagingResponse<Branch> toCustomPagingResponse(
            CustomPage<Branch> branchPage
    ) {
        if (branchPage == null) {
            return null;
        }

        return CustomPagingResponse.<Branch>builder()
                .content(branchPage.getContent())
                .totalElementCount(branchPage.getTotalElementCount())
                .totalPageCount(branchPage.getTotalPageCount())
                .pageNumber(branchPage.getPageNumber())
                .pageSize(branchPage.getPageSize())
                .build();
    }



}
