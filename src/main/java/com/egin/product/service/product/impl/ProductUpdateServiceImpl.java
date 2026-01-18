package com.egin.product.service.product.impl;

import com.egin.product.exception.product.ProductNotFoundException;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductUpdateRequest;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.product.ProductEntityToProductMapper;
import com.egin.product.model.mapper.product.ProductUpdateRequestToProductEntityMapper;
import com.egin.product.repository.ProductRepository;
import com.egin.product.service.product.ProductUpdateService;
import org.springframework.stereotype.Service;

@Service
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    public ProductUpdateServiceImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public Product updateProduct(String productId, ProductUpdateRequest productUpdateRequest) {

        ProductEntity productEntity = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        ProductUpdateRequestToProductEntityMapper.toProductEntity(productUpdateRequest, productEntity);

        ProductEntity updatedProductEntity = this.productRepository
                .save(productEntity);

        return ProductEntityToProductMapper.toProduct(updatedProductEntity);
    }
}
