package com.egin.store.model.mapper;

import com.egin.store.model.dto.request.StoreCreateRequest;
import com.egin.store.model.entity.StoreEntity;
import com.egin.user.model.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StoreCreateRequestToStoreEntityMapper {

    public StoreEntity toStoreEntity(StoreCreateRequest createRequest, UserEntity userEntity) {
        return StoreEntity.builder()
                .brand(createRequest.getBrand())
                .storeAdmin(userEntity)
                .storeContact(createRequest.getStoreContact())
                .description(createRequest.getDescription())
                .status(createRequest.getStatus())
                .storeType(createRequest.getStoreType())
                .build();
    }


}
