package com.egin.inventory.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.inventory.model.Inventory;
import com.egin.inventory.model.entity.InventoryEntity;
import com.egin.product.model.mapper.product.ProductEntityToProductMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryEntityToInventoryMapper {

    public Inventory toInventory(InventoryEntity inventoryEntity) {
        if (inventoryEntity == null) {
            return null;
        }

        return Inventory.builder()
                .id(inventoryEntity.getId())
                .branch(inventoryEntity.getBranchEntity() != null
                        ? BranchEntityToBranchMapper.toBranch(inventoryEntity.getBranchEntity())
                        : null)
                .product(inventoryEntity.getProductEntity() != null
                        ? ProductEntityToProductMapper.toProduct(inventoryEntity.getProductEntity())
                        : null)
                .quantity(inventoryEntity.getQuantity())
                .createdAt(inventoryEntity.getCreatedAt())
                .updatedAt(inventoryEntity.getUpdatedAt())
                .createdBy(inventoryEntity.getCreatedBy())
                .updatedBy(inventoryEntity.getUpdatedBy())
                .build();
    }
}

