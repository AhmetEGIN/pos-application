package com.egin.branch.model.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BranchCreateRequest {

    private String name;
    private String address;
    private String phone;

    private String email;

    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    private String storeEntityId;

    private String userEntityId;

}
