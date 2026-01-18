package com.egin.product.model.mapper.product;

import com.egin.product.model.Product;
import com.egin.product.model.entity.ProductEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListProductEntityToListProductMapper {

    public List<Product> toListProduct(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductEntityToProductMapper::toProduct)
                .toList();
    }


}
