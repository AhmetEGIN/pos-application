package com.egin.inventory.model.mapper;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.inventory.model.Inventory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryByBranchCustomPageToCustomPagingResponseMapper {
    public CustomPagingResponse<Inventory> toCustomPagingResponse(CustomPage<Inventory> inventoryPage) {

        if (inventoryPage == null) {
            return null;
        }

        return CustomPagingResponse.<Inventory>builder()
                .content(inventoryPage.getContent())
                .pageNumber(inventoryPage.getPageNumber())
                .pageSize(inventoryPage.getPageSize())
                .totalElementCount(inventoryPage.getTotalElementCount())
                .totalPageCount(inventoryPage.getTotalPageCount())
                .build();
    }
}
