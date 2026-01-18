package com.egin.product.service.category;

import com.egin.common.model.CustomPage;
import com.egin.product.model.Category;
import com.egin.product.model.dto.request.category.CategoryCreateRequest;
import com.egin.product.model.dto.request.category.CategoryPagingRequest;
import com.egin.product.model.dto.request.category.CategoryUpdateRequest;

public interface CategoryService {


    Category createCategory(final CategoryCreateRequest request);

    CustomPage<Category> getCategoriesByStore(final String storeId, final CategoryPagingRequest request);

    Category updateCategory(final String categoryId, final CategoryUpdateRequest request);

    void deleteCategory(final String categoryId);
}
