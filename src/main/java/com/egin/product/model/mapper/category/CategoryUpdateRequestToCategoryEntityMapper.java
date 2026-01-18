package com.egin.product.model.mapper.category;

import com.egin.product.model.dto.request.category.CategoryUpdateRequest;
import com.egin.product.model.entity.CategoryEntity;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class CategoryUpdateRequestToCategoryEntityMapper {


    public void updateCategoryEntity(CategoryEntity categoryEntity, CategoryUpdateRequest request) {
        Optional.ofNullable(request.getName())
                .ifPresent(categoryEntity::setName);
    }
}
