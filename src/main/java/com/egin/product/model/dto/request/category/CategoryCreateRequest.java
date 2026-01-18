package com.egin.product.model.dto.request.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class CategoryCreateRequest {

    private String name;
    private String storeId;

}
