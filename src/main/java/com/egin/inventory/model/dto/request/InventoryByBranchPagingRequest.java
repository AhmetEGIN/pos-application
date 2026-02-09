package com.egin.inventory.model.dto.request;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class InventoryByBranchPagingRequest extends CustomPagingRequest {
}
