package com.egin.store.controller;

import com.egin.common.model.dto.response.CustomResponse;
import com.egin.store.model.Store;
import com.egin.store.model.dto.request.StoreCreateRequest;
import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.service.StoreService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(
            StoreService storeService
    ) {
        this.storeService = storeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Store> createStore(
            @RequestBody @Valid StoreCreateRequest request
    ) {
        final Store store = storeService.createStore(request);

        return CustomResponse.successOf(store);
    }

    @GetMapping("/{store-id}")
    public CustomResponse<Store> getStoreById(@PathVariable("store-id") String storeId) {
        final Store store = storeService.getStoreById(storeId);
        return CustomResponse.successOf(store);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Store> getStoreByAdmin() {
        final Store store = storeService.getStoreByAdmin();
        return CustomResponse.successOf(store);
    }


    @GetMapping()
    public CustomResponse<List<Store>> getAllStore() {
        final List<Store> store = storeService.getAllStores();
        return CustomResponse.successOf(store);
    }


    @PutMapping("/{store-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Store> updateProductById(
            @RequestBody @Valid final StoreUpdateRequest request,
            @PathVariable(value = "store-id") @UUID final String storeId
    ) {
        final Store updatedProduct = storeService.updateStore(storeId, request);

        return CustomResponse.successOf(updatedProduct);
    }

    @DeleteMapping("/{store-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteStoreById(
            @PathVariable(value = "store-id") @UUID final String storeId
    ) {
        storeService.deleteStore(storeId);
        return CustomResponse.SUCCESS;
    }

    @PutMapping("/{store-id}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Store> updateStoreStatus(
            @PathVariable("store-id") String storeId,
            @RequestParam("status") String status
    ) {
        final Store store = storeService.changeStatus(storeId, status);
        return CustomResponse.successOf(store);
    }

}
