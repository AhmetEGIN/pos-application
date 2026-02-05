package com.egin.store.model;

import com.egin.common.model.BaseModel;
import com.egin.store.model.enums.StoreStatus;
import com.egin.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Store extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @Column(nullable = false)
    private String brand;

    private User storeAdmin;

    private String description;
    private String storeType;
    private StoreStatus status;
    private StoreContact storeContact;


}
