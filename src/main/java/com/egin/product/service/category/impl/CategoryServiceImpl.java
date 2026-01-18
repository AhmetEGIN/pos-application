package com.egin.product.service.category.impl;

import com.egin.common.model.CustomPage;
import com.egin.product.exception.category.CategoryNotFoundException;
import com.egin.product.model.Category;
import com.egin.product.model.dto.request.category.CategoryCreateRequest;
import com.egin.product.model.dto.request.category.CategoryPagingRequest;
import com.egin.product.model.dto.request.category.CategoryUpdateRequest;
import com.egin.product.model.entity.CategoryEntity;
import com.egin.product.model.mapper.category.CategoryCreateRequestToCategoryEntityMapper;
import com.egin.product.model.mapper.category.CategoryEntityToCategoryMapper;
import com.egin.product.model.mapper.category.CategoryUpdateRequestToCategoryEntityMapper;
import com.egin.product.model.mapper.category.ListCategoryEntityToListCategoryMapper;
import com.egin.product.repository.CategoryRepository;
import com.egin.product.service.category.CategoryService;
import com.egin.store.model.Store;
import com.egin.store.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreService storeService;

    public CategoryServiceImpl(
            final CategoryRepository categoryRepository,
            StoreService storeService
    ) {
        this.categoryRepository = categoryRepository;
        this.storeService = storeService;
    }

    @Override
    public Category createCategory(final CategoryCreateRequest request) {
        final Store store = this.storeService.getStoreById(request.getStoreId());
        final CategoryEntity categoryEntity = CategoryCreateRequestToCategoryEntityMapper
                .toCategoryEntity(request, store);
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);


        return CategoryEntityToCategoryMapper.toCategory(savedCategoryEntity);
    }

    @Override
    public CustomPage<Category> getCategoriesByStore(final String storeId, final CategoryPagingRequest request) {
        final Page<CategoryEntity> categoryEntitiesPage = categoryRepository
                .findAllByStoreId(storeId, request.toPageable());

        if (categoryEntitiesPage.getContent().isEmpty()) {
            throw new CategoryNotFoundException();
        }
        final List<Category> categories = ListCategoryEntityToListCategoryMapper
                .toListCategory(categoryEntitiesPage.getContent());


        return CustomPage.of(categories, categoryEntitiesPage);
    }

    @Override
    public Category updateCategory(final String categoryId, final CategoryUpdateRequest request) {
        final CategoryEntity categoryEntity = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        CategoryUpdateRequestToCategoryEntityMapper
                .updateCategoryEntity(categoryEntity, request);
        CategoryEntity updatedCategoryEntity = categoryRepository.save(categoryEntity);

        return CategoryEntityToCategoryMapper.toCategory(updatedCategoryEntity);
    }

    @Override
    public void deleteCategory(final String categoryId) {
        final CategoryEntity categoryEntity = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        categoryRepository.delete(categoryEntity);
    }
}
