package com.egin.inventory.repository;

import com.egin.inventory.model.entity.InventoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InventoryRepository extends JpaRepository<InventoryEntity, String> {

    Optional<InventoryEntity> findByProductEntityIdAndBranchEntityId(String productEntityId, String branchEntityId);

    Page<InventoryEntity> findAllByBranchEntityId(String branchEntityId, Pageable pageable);




}
