package com.egin.user.repository;

import com.egin.user.model.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Page<CustomerEntity> findAll(Pageable pageable);

}

