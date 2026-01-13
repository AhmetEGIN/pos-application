package com.egin.store.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class StoreContact {

    private String address;
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

}
