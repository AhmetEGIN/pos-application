package com.egin.user.model;

import com.egin.auth.model.enums.UserStatus;
import com.egin.auth.model.enums.UserType;
import com.egin.common.model.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private UserType userType;

    @Builder.Default
    private UserStatus userStatus = UserStatus.ACTIVE;
}
