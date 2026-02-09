package com.egin.inventory.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.inventory.model.dto.request.InventoryCreateRequest;
import com.egin.inventory.model.entity.InventoryEntity;
import com.egin.product.model.Product;
import com.egin.product.model.mapper.product.ProductToProductEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InventoryCreateRequestToInventoryEntityMapper {

    public InventoryEntity toInventoryEntity(
            final InventoryCreateRequest inventoryCreateRequest,
            final Branch branch,
            final Product product
            ) {

        return InventoryEntity.builder()
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .productEntity(ProductToProductEntityMapper.toProductEntity(product))
                .quantity(inventoryCreateRequest.getQuantity())
                .build();
    }


}
