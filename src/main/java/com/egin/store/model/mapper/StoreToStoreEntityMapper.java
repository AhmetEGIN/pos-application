package com.egin.store.model.mapper;

import com.egin.store.model.Store;
import com.egin.store.model.entity.StoreEntity;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreToStoreEntityMapper {

    public StoreEntity toStoreEntity(final Store store) {
        if (store == null) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(store.getId());
        storeEntity.setBrand(store.getBrand());
        storeEntity.setDescription(store.getDescription());
        storeEntity.setStoreType(store.getStoreType());
        storeEntity.setStatus(store.getStatus());
        storeEntity.setStoreContact(store.getStoreContact());
        storeEntity.setStoreAdmin(UserToUserEntityMapper.toUserEntity(store.getStoreAdmin()));
        return storeEntity;
    }


}
