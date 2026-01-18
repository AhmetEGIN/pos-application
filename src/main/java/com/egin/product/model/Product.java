package com.egin.product.model;

import com.egin.common.model.BaseModel;
import com.egin.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends BaseModel {

    private String id;

    private String name;
    private String sku;

    private String description;

    private Double mrp;
    private Double sellingPrice;
    private String brand;
    private String imageUrl;
    private Store store;
    private Category category;

}
