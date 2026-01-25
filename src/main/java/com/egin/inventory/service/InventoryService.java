package com.egin.inventory.service;

import com.egin.common.model.CustomPage;
import com.egin.inventory.model.Inventory;
import com.egin.inventory.model.dto.request.InventoryByBranchPagingRequest;
import com.egin.inventory.model.dto.request.InventoryCreateRequest;
import com.egin.inventory.model.dto.request.InventoryUpdateRequest;

import java.util.List;

public interface InventoryService {

    Inventory createInventory(final InventoryCreateRequest inventoryCreateRequest);
    Inventory updateInventory(final String inventoryId, final InventoryUpdateRequest inventoryUpdateRequest);
    void deleteInventory(final String inventoryId);
    Inventory getInventoryById(final String inventoryId);
    Inventory getInventoryByProductIdAndBranchId(final String productId, final String branchId);
    CustomPage<Inventory> getAllInventoryByBranchId(final String branchId, final InventoryByBranchPagingRequest request);


}
