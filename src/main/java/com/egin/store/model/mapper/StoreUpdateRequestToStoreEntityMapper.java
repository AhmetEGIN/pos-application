package com.egin.store.model.mapper;

import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.model.entity.StoreEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreUpdateRequestToStoreEntityMapper {

    public StoreEntity toStoreEntity(StoreEntity storeEntity, StoreUpdateRequest storeUpdateRequest) {
        if (storeEntity == null) {
            return null;
        }
        if (storeUpdateRequest == null) {
            return storeEntity;
        }

        if (storeUpdateRequest.getBrand() != null) {
            storeEntity.setBrand(storeUpdateRequest.getBrand());
        }
        if (storeUpdateRequest.getDescription() != null) {
            storeEntity.setDescription(storeUpdateRequest.getDescription());
        }
        if (storeUpdateRequest.getStoreType() != null) {
            storeEntity.setStoreType(storeUpdateRequest.getStoreType());
        }
        if (storeUpdateRequest.getStatus() != null) {
            storeEntity.setStatus(storeUpdateRequest.getStatus());
        }
        if (storeUpdateRequest.getStoreContact() != null) {
            storeEntity.setStoreContact(storeUpdateRequest.getStoreContact());
        }

        return storeEntity;
    }


}
