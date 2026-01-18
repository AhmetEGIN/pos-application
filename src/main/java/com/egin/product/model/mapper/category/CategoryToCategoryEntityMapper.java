package com.egin.product.model.mapper.category;

import com.egin.product.model.Category;
import com.egin.product.model.entity.CategoryEntity;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryToCategoryEntityMapper {
    public CategoryEntity toCategoryEntity(Category category) {

        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .storeEntity(StoreToStoreEntityMapper.toStoreEntity(category.getStore()))
                .build();

    }
}
