package com.egin.product.model.dto.request.category;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CategoryPagingRequest extends CustomPagingRequest {
}
