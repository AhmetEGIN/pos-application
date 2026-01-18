package com.egin.product.controller;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.product.model.Category;
import com.egin.product.model.dto.request.category.CategoryCreateRequest;
import com.egin.product.model.dto.request.category.CategoryPagingRequest;
import com.egin.product.model.dto.request.category.CategoryUpdateRequest;
import com.egin.product.model.mapper.category.CategoryCustomPageToCustomPagingResponseMapper;
import com.egin.product.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'STORE_MANAGER')")
    public CustomResponse<Category> createCategory(
            @RequestBody @Valid final CategoryCreateRequest request
    ) {
        final Category createdCategory = categoryService
                .createCategory(request);

        return CustomResponse.successOf(createdCategory);
    }

    @GetMapping("/store/{store-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Category>> getCategoriesByStore(
            @PathVariable("store-id") final String storeId,
            @Valid final CategoryPagingRequest request
    ) {
        final CustomPage<Category> categoryPage = categoryService
                .getCategoriesByStore(storeId, request);

        final CustomPagingResponse<Category> response = CategoryCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(categoryPage);

        return CustomResponse.successOf(response);
    }


    @PutMapping("/{category-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'STORE_MANAGER')")
    public CustomResponse<Category> updateCategory(
            @PathVariable("category-id") final String categoryId,
            @RequestBody @Valid final CategoryUpdateRequest request
    ) {
        final Category updatedCategory = categoryService
                .updateCategory(categoryId, request);

        return CustomResponse.successOf(updatedCategory);
    }


    @DeleteMapping("/{category-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'STORE_MANAGER')")
    public CustomResponse<Void> deleteCategory(
            @PathVariable("category-id") final String categoryId
    ) {
        categoryService.deleteCategory(categoryId);
        return CustomResponse.SUCCESS;
    }

}
