package com.egin.product.service.product.impl;

import com.egin.common.model.CustomPage;
import com.egin.product.exception.product.ProductNotFoundException;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductPagingByStoreRequest;
import com.egin.product.model.dto.request.product.SearchProductPagingRequest;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.product.ListProductEntityToListProductMapper;
import com.egin.product.model.mapper.product.ProductEntityToProductMapper;
import com.egin.product.repository.ProductRepository;
import com.egin.product.service.product.ProductReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ProductReadServiceImpl implements ProductReadService {

    private final ProductRepository productRepository;

    public ProductReadServiceImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(String productId) {
        final ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        return ProductEntityToProductMapper.toProduct(productEntity);
    }

    @Override
    public CustomPage<Product> getProductsByStoreId(final String storeId, final  ProductPagingByStoreRequest request) {
        final Page<ProductEntity> productEntityPage = this.productRepository.findByStoreEntityId(
                storeId,
                request.toPageable()
        );
        if (productEntityPage.getContent().isEmpty()) {
            throw new ProductNotFoundException();
        }

        final List<Product> products = ListProductEntityToListProductMapper
                .toListProduct(productEntityPage.getContent());
        return CustomPage.of(products, productEntityPage);
    }

    @Override
    public CustomPage<Product> searchByKeyword(SearchProductPagingRequest request) {
        return null;
    }

    @Override
    public List<Product> getTopSellingProductsByCashierAndDateRange(
            final String cashierId,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final int limit
    ) {
        Pageable pageable = PageRequest.of(0, limit);
        List<ProductEntity> topProducts = productRepository.findTopSellingProductsByCashierAndDateRange(
                cashierId, startDate, endDate, pageable
        );

        if (topProducts == null || topProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return ListProductEntityToListProductMapper.toListProduct(topProducts);
    }
}
