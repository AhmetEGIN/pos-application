package com.egin.store.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.store.model.Store;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreCustomPageToCustomPagingResponseMapper {


    public CustomPagingResponse<Store> toCustomPagingResponse(CustomPage<Store> storeByAdmin) {

        if (storeByAdmin == null) {
            return null;
        }
        return CustomPagingResponse.<Store>builder()
                .content(storeByAdmin.getContent())
                .totalElementCount(storeByAdmin.getTotalElementCount())
                .totalPageCount(storeByAdmin.getTotalPageCount())
                .pageNumber(storeByAdmin.getPageNumber())
                .pageSize(storeByAdmin.getPageSize())
                .build();
    }
}
