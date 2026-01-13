package com.egin.store.model.mapper;

import com.egin.store.model.Store;
import com.egin.store.model.entity.StoreEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListStoreEntityToListStoreMapper {


    public static List<Store> toStoreList(final List<StoreEntity> storeEntities) {
        return storeEntities.stream()
                .map(StoreEntityToStoreMapper::toStore)
                .toList();

    }
}
