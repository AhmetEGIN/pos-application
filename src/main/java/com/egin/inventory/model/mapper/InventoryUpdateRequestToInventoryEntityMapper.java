package com.egin.inventory.model.mapper;

import com.egin.inventory.model.dto.request.InventoryUpdateRequest;
import com.egin.inventory.model.entity.InventoryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryUpdateRequestToInventoryEntityMapper {

    public void toInventoryEntity(
            InventoryUpdateRequest inventoryUpdateRequest,
            InventoryEntity inventoryEntity
    ) {
        if (inventoryUpdateRequest.getQuantity() != null) {
            inventoryEntity.setQuantity(inventoryUpdateRequest.getQuantity());
        }
    }
}

