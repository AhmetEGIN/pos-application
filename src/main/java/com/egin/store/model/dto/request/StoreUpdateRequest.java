package com.egin.store.model.dto.request;

import com.egin.store.model.StoreContact;
import com.egin.store.model.enums.StoreStatus;
import com.egin.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreUpdateRequest {

    private String brand;
    private String storeAdminId;
    private String description;
    private String storeType;
    private StoreStatus status;
    private StoreContact storeContact;


}
