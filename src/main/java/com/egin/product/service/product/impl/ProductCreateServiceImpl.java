package com.egin.product.service.product.impl;

import com.egin.product.model.Category;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductCreateRequest;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.product.ProductCreateRequestToProductEntityMapper;
import com.egin.product.model.mapper.product.ProductEntityToProductMapper;
import com.egin.product.repository.ProductRepository;
import com.egin.product.service.category.CategoryService;
import com.egin.product.service.product.ProductCreateService;
import com.egin.store.model.Store;
import com.egin.store.service.StoreService;
import org.springframework.stereotype.Service;

@Service
public class ProductCreateServiceImpl implements ProductCreateService {


    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final CategoryService categoryService;

    public ProductCreateServiceImpl(
            ProductRepository productRepository,
            StoreService storeService,
            CategoryService categoryService
    ) {
        this.productRepository = productRepository;
        this.storeService = storeService;
        this.categoryService = categoryService;
    }

    @Override
    public Product createProduct(final ProductCreateRequest request) {
        final Store store = storeService.getStoreById(request.getStoreEntityId());
        final Category category = categoryService.getCategoryById(request.getCategoryEntityId());

        ProductEntity productEntityToBeSave = ProductCreateRequestToProductEntityMapper
                .toProductEntity(request, store, category);

        ProductEntity savedProductEntity = productRepository.save(productEntityToBeSave);


        return ProductEntityToProductMapper.toProduct(savedProductEntity);
    }
}
