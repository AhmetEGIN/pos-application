package com.egin.product.model.mapper.category;

import com.egin.product.model.Category;
import com.egin.product.model.entity.CategoryEntity;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryEntityToCategoryMapper {


    public Category toCategory(final CategoryEntity categoryEntity) {

        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .store(StoreEntityToStoreMapper.toStore(categoryEntity.getStoreEntity()))
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .createdBy(categoryEntity.getCreatedBy())
                .updatedBy(categoryEntity.getUpdatedBy())
                .build();
    }
}
