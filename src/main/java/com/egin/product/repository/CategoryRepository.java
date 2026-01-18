package com.egin.product.repository;

import com.egin.product.model.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    Page<CategoryEntity> findAllByStoreEntityId(String storeId, Pageable pageable);
}
