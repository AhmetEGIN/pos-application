package com.egin.inventory.model;

import com.egin.branch.model.Branch;
import com.egin.common.model.BaseModel;
import com.egin.product.model.Product;
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
public class Inventory extends BaseModel {

    private String id;

    private Branch branch;

    private Product product;

    private Integer quantity;


}
