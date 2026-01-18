package com.egin.product.service.product;

import com.egin.common.model.CustomPage;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductPagingByStoreRequest;
import com.egin.product.model.dto.request.product.SearchProductPagingRequest;

public interface ProductReadService {

    CustomPage<Product> getProductsByStoreId(final String storeId, final ProductPagingByStoreRequest request);
    CustomPage<Product> searchByKeyword(final SearchProductPagingRequest request);


}
