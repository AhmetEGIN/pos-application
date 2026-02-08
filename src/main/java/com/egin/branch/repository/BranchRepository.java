package com.egin.branch.repository;

import com.egin.branch.model.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BranchRepository extends JpaRepository<BranchEntity, String> {

    @Query("SELECT b FROM BranchEntity b LEFT JOIN FETCH b.workingDays WHERE b.storeEntity.id = :storeEntityId")
    Page<BranchEntity> findAllByStoreEntityId(@Param("storeEntityId") String storeEntityId, Pageable pageable);

    @Query("SELECT b FROM BranchEntity b LEFT JOIN FETCH b.workingDays WHERE b.id = :id")
    Optional<BranchEntity> findByIdWithWorkingDays(@Param("id") String id);

}
