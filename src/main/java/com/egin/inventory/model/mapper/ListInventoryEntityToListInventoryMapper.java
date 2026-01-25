package com.egin.inventory.model.mapper;

import com.egin.inventory.model.Inventory;
import com.egin.inventory.model.entity.InventoryEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListInventoryEntityToListInventoryMapper {

    public List<Inventory> toListInventory(List<InventoryEntity> inventoryEntities) {
        if (inventoryEntities == null) {
            return null;
        }

        return inventoryEntities.stream()
                .map(InventoryEntityToInventoryMapper::toInventory)
                .collect(Collectors.toList());
    }
}

