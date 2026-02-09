package com.egin.product.model.mapper.product;

import com.egin.product.model.Product;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.category.CategoryToCategoryEntityMapper;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductToProductEntityMapper {
    public ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .imageUrl(product.getImageUrl())
                .storeEntity(StoreToStoreEntityMapper.toStoreEntity(product.getStore()))
                .categoryEntity(CategoryToCategoryEntityMapper.toCategoryEntity(product.getCategory()))
                .build();
    }
}
