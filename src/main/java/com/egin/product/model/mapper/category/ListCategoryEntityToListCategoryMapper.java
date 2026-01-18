package com.egin.product.model.mapper.category;

import com.egin.product.model.Category;
import com.egin.product.model.entity.CategoryEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListCategoryEntityToListCategoryMapper {


    public static List<Category> toListCategory(List<CategoryEntity> content) {
        return content.stream()
                .map(CategoryEntityToCategoryMapper::toCategory)
                .toList();
    }
}
