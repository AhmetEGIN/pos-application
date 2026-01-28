package com.egin.product.service.product;

import com.egin.common.model.CustomPage;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductPagingByStoreRequest;
import com.egin.product.model.dto.request.product.SearchProductPagingRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductReadService {

    Product getProductById(String productId);

    CustomPage<Product> getProductsByStoreId(String storeId, ProductPagingByStoreRequest request);

    CustomPage<Product> searchByKeyword(SearchProductPagingRequest request);

    // Shift report calculations
    List<Product> getTopSellingProductsByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate, int limit);

}
