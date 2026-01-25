package com.egin.inventory.model.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryUpdateRequest {

    private String branchEntityId;

    private String productEntityId;

    private Integer quantity;

}
