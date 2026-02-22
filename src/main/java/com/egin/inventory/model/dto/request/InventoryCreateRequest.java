package com.egin.inventory.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryCreateRequest {

    @NotBlank(message = "Branch ID cannot be blank")
    private String branchEntityId;

    @NotBlank(message = "Product ID cannot be blank")
    private String productEntityId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;


}
