package com.egin.product.model.dto.request.product;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SearchProductPagingRequest extends CustomPagingRequest {

    private String storeEntityId;
    private String keyword;

}
