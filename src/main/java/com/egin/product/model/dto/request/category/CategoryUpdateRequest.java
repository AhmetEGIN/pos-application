package com.egin.product.model.dto.request.category;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryUpdateRequest {

    private String name;
    private String storeId;

}
