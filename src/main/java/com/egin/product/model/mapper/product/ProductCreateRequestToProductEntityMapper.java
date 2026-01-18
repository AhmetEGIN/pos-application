package com.egin.product.model.mapper.product;

import com.egin.product.model.Category;
import com.egin.product.model.dto.request.product.ProductCreateRequest;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.category.CategoryToCategoryEntityMapper;
import com.egin.store.model.Store;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductCreateRequestToProductEntityMapper {

    public ProductEntity toProductEntity(
            final ProductCreateRequest request,
            final Store store,
            final Category category
    ) {
        if (request == null) {
            return null;
        }

        ProductEntity productEntity = ProductEntity.builder()
                .name(request.getName())
                .sku(request.getSku())
                .description(request.getDescription())
                .mrp(request.getMrp())
                .sellingPrice(request.getSellingPrice())
                .brand(request.getBrand())
                .imageUrl(request.getImageUrl())
                .storeEntity(StoreToStoreEntityMapper.toStoreEntity(store))
                .categoryEntity(CategoryToCategoryEntityMapper.toCategoryEntity(category)) // Category mapping can be added here when available

                .build();
        return productEntity;
    }


}
