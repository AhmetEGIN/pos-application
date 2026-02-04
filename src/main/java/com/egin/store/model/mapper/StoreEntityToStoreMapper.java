package com.egin.store.model.mapper;

import com.egin.store.model.Store;
import com.egin.store.model.entity.StoreEntity;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreEntityToStoreMapper {

    public Store toStore(StoreEntity storeEntity) {
        return Store.builder()
                .id(storeEntity.getId())
                .brand(storeEntity.getBrand())
                .storeAdmin(UserEntityToUserMapper.toUser(storeEntity.getStoreAdmin()))
                .description(storeEntity.getDescription())
                .storeType(storeEntity.getStoreType())
                .status(storeEntity.getStatus())
                .storeContact(storeEntity.getStoreContact())
                .createdAt(storeEntity.getCreatedAt())
                .updatedAt(storeEntity.getUpdatedAt())
                .createdBy(storeEntity.getCreatedBy())
                .updatedBy(storeEntity.getUpdatedBy())
                .build();
    }


}
