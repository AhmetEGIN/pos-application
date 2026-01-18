package com.egin.product.service.product.impl;

import com.egin.product.exception.product.ProductNotFoundException;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.repository.ProductRepository;
import com.egin.product.service.product.ProductDeleteService;
import org.springframework.stereotype.Service;

@Service
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    public ProductDeleteServiceImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }


    @Override
    public void deleteProduct(String productId) {

        final ProductEntity productEntity = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        this.productRepository.delete(productEntity);

    }
}
