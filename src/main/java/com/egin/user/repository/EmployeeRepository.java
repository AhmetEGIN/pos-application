package com.egin.user.repository;

import com.egin.user.model.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    Optional<EmployeeEntity> findByEmail(String email);

    Page<EmployeeEntity> findByStoreId(String storeId, Pageable pageable);

    Page<EmployeeEntity> findByBranchId(String branchId, Pageable pageable);

    boolean existsByEmail(String email);
}

