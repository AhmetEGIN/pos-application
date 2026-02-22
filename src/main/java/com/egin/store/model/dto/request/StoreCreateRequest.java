package com.egin.store.model.dto.request;

import com.egin.store.model.StoreContact;
import com.egin.store.model.enums.StoreStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreCreateRequest {

    @NotBlank(message = "Brand name cannot be blank")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String brand;

    @NotBlank(message = "User ID cannot be blank")
    private String userId;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Store type is required")
    @Size(max = 50, message = "Store type cannot exceed 50 characters")
    private String storeType;

    @NotNull(message = "Store status is required")
    private StoreStatus status;

    @Valid
    @NotNull(message = "Store contact information is required")
    private StoreContact storeContact;

}
