package com.egin.product.model.mapper.product;

import com.egin.product.model.dto.request.product.ProductUpdateRequest;
import com.egin.product.model.entity.ProductEntity;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ProductUpdateRequestToProductEntityMapper {

    public ProductEntity toProductEntity(
            ProductUpdateRequest productUpdateRequest,
            ProductEntity productEntity
    ) {
        Optional.ofNullable(productUpdateRequest.getName())
                .ifPresent(productEntity::setName);

        Optional.ofNullable(productUpdateRequest.getSku())
                .ifPresent(productEntity::setSku);

        Optional.ofNullable(productUpdateRequest.getDescription())
                .ifPresent(productEntity::setDescription);

        Optional.ofNullable(productUpdateRequest.getMrp())
                .ifPresent(productEntity::setMrp);

        Optional.ofNullable(productUpdateRequest.getSellingPrice())
                .ifPresent(productEntity::setSellingPrice);

        Optional.ofNullable(productUpdateRequest.getBrand())
                .ifPresent(productEntity::setBrand);

        Optional.ofNullable(productUpdateRequest.getImageUrl())
                .ifPresent(productEntity::setImageUrl);

        return productEntity;
    }


}
