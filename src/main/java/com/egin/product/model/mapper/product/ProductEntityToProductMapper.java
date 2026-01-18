package com.egin.product.model.mapper.product;

import com.egin.product.model.Product;
import com.egin.product.model.entity.ProductEntity;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductEntityToProductMapper {

    public Product toProduct(final ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .store(StoreEntityToStoreMapper.toStore(productEntity.getStoreEntity()))
                .name(productEntity.getName())
                .sku(productEntity.getSku())
                .description(productEntity.getDescription())
                .mrp(productEntity.getMrp())
                .sellingPrice(productEntity.getSellingPrice())
                .imageUrl(productEntity.getImageUrl())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }


}
