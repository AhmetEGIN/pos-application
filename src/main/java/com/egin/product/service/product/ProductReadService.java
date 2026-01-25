package com.egin.product.service.product;

import com.egin.common.model.CustomPage;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductPagingByStoreRequest;
import com.egin.product.model.dto.request.product.SearchProductPagingRequest;

public interface ProductReadService {

    Product getProductById(String productId);

    CustomPage<Product> getProductsByStoreId(String storeId, ProductPagingByStoreRequest request);

    CustomPage<Product> searchByKeyword(SearchProductPagingRequest request);

}
