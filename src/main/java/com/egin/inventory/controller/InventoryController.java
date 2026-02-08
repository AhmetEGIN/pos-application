package com.egin.inventory.controller;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.inventory.model.Inventory;
import com.egin.inventory.model.dto.request.InventoryByBranchPagingRequest;
import com.egin.inventory.model.dto.request.InventoryCreateRequest;
import com.egin.inventory.model.dto.request.InventoryUpdateRequest;
import com.egin.inventory.model.mapper.InventoryByBranchCustomPageToCustomPagingResponseMapper;
import com.egin.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService
    ) {
        this.inventoryService = inventoryService;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Inventory> createInventory(
            @RequestBody @Valid final InventoryCreateRequest request
    ) {
        final Inventory inventory = inventoryService.createInventory(request);

        return CustomResponse.successOf(inventory);
    }


    @PutMapping("/{inventory-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Inventory> updateInventory(
            @PathVariable("inventory-id") final String inventoryId,
            @RequestBody @Valid final InventoryUpdateRequest request
    ) {
        final Inventory inventory = inventoryService.updateInventory(inventoryId, request);

        return CustomResponse.successOf(inventory);
    }


    @DeleteMapping("/{inventory-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteInventory(
            @PathVariable("inventory-id") final String inventoryId
    ) {
        inventoryService.deleteInventory(inventoryId);
        return CustomResponse.SUCCESS;
    }

    @GetMapping("/{inventory-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Inventory> getInventoryById(
            @PathVariable("inventory-id") final String inventoryId
    ) {
        final Inventory inventory = inventoryService.getInventoryById(inventoryId);
        return CustomResponse.successOf(inventory);
    }


    @GetMapping("/product/{product-id}/branch/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Inventory> getInventoryByProductIdAndBranchId(
            @PathVariable("product-id") final String productId,
            @PathVariable("branch-id") final String branchId
    ) {
        final Inventory inventory = inventoryService.getInventoryByProductIdAndBranchId(productId, branchId);
        return CustomResponse.successOf(inventory);
    }

    @GetMapping("/branch/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Inventory>> getAllInventoryByBranchId(
            @PathVariable("branch-id") final String branchId,
            @RequestBody @Valid final InventoryByBranchPagingRequest request
    ) {
        final CustomPagingResponse<Inventory> inventoryPage = InventoryByBranchCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        inventoryService.getAllInventoryByBranchId(branchId, request)
                );
        return CustomResponse.successOf(inventoryPage);


    }
}
