package com.egin.branch.model;

import com.egin.common.model.BaseModel;
import com.egin.store.model.Store;
import com.egin.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Branch extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String address;
    private String phone;

    private String email;

    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    private Store store;

    private User manager;


}
