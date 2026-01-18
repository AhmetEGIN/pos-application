package com.egin.product.service.product;

import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductCreateRequest;

public interface ProductCreateService {

    Product createProduct(final ProductCreateRequest request);

}
