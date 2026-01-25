package com.egin.branch.repository;

import com.egin.branch.model.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<BranchEntity, String> {

    Page<BranchEntity> findAllByStoreEntityId(final String storeEntityId, final Pageable pageable);

}
