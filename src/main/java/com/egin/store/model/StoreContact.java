package com.egin.store.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class StoreContact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

}
