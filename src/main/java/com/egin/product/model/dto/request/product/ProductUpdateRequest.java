package com.egin.product.model.dto.request.product;

import com.egin.common.validation.ValidPrice;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ValidPrice
public class ProductUpdateRequest {

    @Size(min = 2, max = 255, message = "Product name must be between 2 and 255 characters")
    private String name;

    @Pattern(regexp = "^[A-Z0-9-]+$", message = "SKU must contain only uppercase letters, numbers and hyphens")
    @Size(max = 50, message = "SKU cannot exceed 50 characters")
    private String sku;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "MRP must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid MRP format")
    private Double mrp;

    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid selling price format")
    private Double sellingPrice;

    @Size(max = 100, message = "Brand name cannot exceed 100 characters")
    private String brand;

    @Pattern(regexp = "^(https?://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$",
             message = "Invalid image URL format")
    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String imageUrl;

    private String categoryEntityId;

    private String storeEntityId;

}
