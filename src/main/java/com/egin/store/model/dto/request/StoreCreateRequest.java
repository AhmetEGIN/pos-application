package com.egin.store.model.dto.request;

import com.egin.store.model.StoreContact;
import com.egin.store.model.enums.StoreStatus;
import com.egin.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreCreateRequest {

    private String brand;
    private String userId;
    private String description;
    private String storeType;
    private StoreStatus status;
    private StoreContact storeContact;

}
