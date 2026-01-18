package com.egin.store.model.mapper;

import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.model.entity.StoreEntity;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class StoreUpdateRequestToStoreEntityMapper {

    public StoreEntity toStoreEntity(StoreEntity storeEntity, StoreUpdateRequest storeUpdateRequest) {
        if (storeEntity == null) {
            return null;
        }
        if (storeUpdateRequest == null) {
            return storeEntity;
        }

//        if (storeUpdateRequest.getBrand() != null) {
//            storeEntity.setBrand(storeUpdateRequest.getBrand());
//        }
//        if (storeUpdateRequest.getDescription() != null) {
//            storeEntity.setDescription(storeUpdateRequest.getDescription());
//        }
//        if (storeUpdateRequest.getStoreType() != null) {
//            storeEntity.setStoreType(storeUpdateRequest.getStoreType());
//        }
//        if (storeUpdateRequest.getStatus() != null) {
//            storeEntity.setStatus(storeUpdateRequest.getStatus());
//        }
//        if (storeUpdateRequest.getStoreContact() != null) {
//            storeEntity.setStoreContact(storeUpdateRequest.getStoreContact());
//        }

        Optional.ofNullable(storeUpdateRequest.getBrand())
                .ifPresent(storeEntity::setBrand);
        Optional.ofNullable(storeUpdateRequest.getDescription())
                .ifPresent(storeEntity::setDescription);
        Optional.ofNullable(storeUpdateRequest.getStoreType())
                .ifPresent(storeEntity::setStoreType);
        Optional.ofNullable(storeUpdateRequest.getStatus())
                .ifPresent(storeEntity::setStatus);
        Optional.ofNullable(storeUpdateRequest.getStoreContact())
                .ifPresent(storeEntity::setStoreContact);


        return storeEntity;
    }


}
