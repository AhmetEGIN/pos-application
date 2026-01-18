package com.egin.product.service.product;

import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductUpdateRequest;

public interface ProductUpdateService {

    Product updateProduct(final String productId, final ProductUpdateRequest productUpdateRequest);


}
