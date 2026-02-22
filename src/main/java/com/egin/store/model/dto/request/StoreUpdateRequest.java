package com.egin.store.model.dto.request;

import com.egin.store.model.StoreContact;
import com.egin.store.model.enums.StoreStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreUpdateRequest {

    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String brand;

    private String storeAdminId;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Size(max = 50, message = "Store type cannot exceed 50 characters")
    private String storeType;

    private StoreStatus status;

    @Valid
    private StoreContact storeContact;


}
