package com.egin.product.model.mapper.category;

import com.egin.product.model.dto.request.category.CategoryCreateRequest;
import com.egin.product.model.entity.CategoryEntity;
import com.egin.store.model.Store;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryCreateRequestToCategoryEntityMapper {

    public CategoryEntity toCategoryEntity(
            final CategoryCreateRequest categoryCreateRequest,
            final Store store
            ) {

        return CategoryEntity.builder()
                .name(categoryCreateRequest.getName())
                .store(StoreToStoreEntityMapper.toStoreEntity(store))
                .build();


    }


}
