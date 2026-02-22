package com.egin.inventory.model.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryUpdateRequest {

    private String branchEntityId;

    private String productEntityId;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

}
