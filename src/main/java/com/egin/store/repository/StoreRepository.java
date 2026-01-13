package com.egin.store.repository;

import com.egin.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, String> {

    StoreEntity findByStoreAdminId(String storeAdminId);



}
