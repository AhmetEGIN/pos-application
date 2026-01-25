package com.egin.inventory.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.inventory.exception.InventoryNotFoundException;
import com.egin.inventory.model.Inventory;
import com.egin.inventory.model.dto.request.InventoryByBranchPagingRequest;
import com.egin.inventory.model.dto.request.InventoryCreateRequest;
import com.egin.inventory.model.dto.request.InventoryUpdateRequest;
import com.egin.inventory.model.entity.InventoryEntity;
import com.egin.inventory.model.mapper.InventoryCreateRequestToInventoryEntityMapper;
import com.egin.inventory.repository.InventoryRepository;
import com.egin.inventory.service.InventoryService;
import com.egin.inventory.model.mapper.InventoryEntityToInventoryMapper;
import com.egin.inventory.model.mapper.InventoryUpdateRequestToInventoryEntityMapper;
import com.egin.inventory.model.mapper.ListInventoryEntityToListInventoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import com.egin.product.model.Product;
import com.egin.product.service.product.ProductReadService;
import org.springframework.data.domain.Page;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchService branchService;
    private final ProductReadService productReadService;

    public InventoryServiceImpl(
            final InventoryRepository inventoryRepository,
            final BranchService branchService,
            final ProductReadService productReadService
    ) {
        this.inventoryRepository = inventoryRepository;
        this.branchService = branchService;
        this.productReadService = productReadService;
    }

    @Override
    public Inventory createInventory(final InventoryCreateRequest inventoryCreateRequest) {

        final Branch branch = branchService.getBranchById(inventoryCreateRequest.getBranchEntityId());

        final Product product = productReadService.getProductById(inventoryCreateRequest.getProductEntityId());

        final InventoryEntity inventoryEntity = InventoryCreateRequestToInventoryEntityMapper
                .toInventoryEntity(
                        inventoryCreateRequest,
                        branch,
                        product
                );

        final InventoryEntity savedInventoryEntity = this.inventoryRepository.save(inventoryEntity);

        return InventoryEntityToInventoryMapper.toInventory(savedInventoryEntity);
    }

    @Override
    public Inventory updateInventory(final String inventoryId, final InventoryUpdateRequest inventoryUpdateRequest) {
        final InventoryEntity inventoryEntity = this.inventoryRepository
                .findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);

        InventoryUpdateRequestToInventoryEntityMapper.toInventoryEntity(inventoryUpdateRequest, inventoryEntity);

        final InventoryEntity updatedInventoryEntity = this.inventoryRepository.save(inventoryEntity);

        return InventoryEntityToInventoryMapper.toInventory(updatedInventoryEntity);
    }

    @Override
    public void deleteInventory(final String inventoryId) {

        final InventoryEntity inventoryEntity = this.inventoryRepository
                .findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);

        this.inventoryRepository.delete(inventoryEntity);
    }

    @Override
    public Inventory getInventoryById(final String inventoryId) {

        final InventoryEntity inventoryEntity = this.inventoryRepository
                .findById(inventoryId)
                .orElseThrow(InventoryNotFoundException::new);

        return InventoryEntityToInventoryMapper.toInventory(inventoryEntity);
    }

    @Override
    public Inventory getInventoryByProductIdAndBranchId(final String productId, final String branchId) {

        final InventoryEntity inventoryEntity = this.inventoryRepository
                .findByProductEntityIdAndBranchEntityId(productId, branchId)
                .orElseThrow(InventoryNotFoundException::new);

        return InventoryEntityToInventoryMapper.toInventory(inventoryEntity);
    }

    @Override
    public CustomPage<Inventory> getAllInventoryByBranchId(final String branchId, final InventoryByBranchPagingRequest request) {

        Page<InventoryEntity> inventoryEntityPage = this.inventoryRepository
                .findAllByBranchEntityId(
                        branchId,
                        request.toPageable()
                );
        final List<Inventory> inventories = ListInventoryEntityToListInventoryMapper
                .toListInventory(inventoryEntityPage.getContent());

        return CustomPage.of(inventories, inventoryEntityPage);

    }
}
