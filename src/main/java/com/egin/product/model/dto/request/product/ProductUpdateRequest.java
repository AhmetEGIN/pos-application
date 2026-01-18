package com.egin.product.model.dto.request.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductUpdateRequest {


    private String name;
    private String sku;
    private String description;
    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String imageUrl;
    private String categoryEntityId;
    private String storeEntityId;

}
