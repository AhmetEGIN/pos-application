package com.egin.user.model.dto.request;

import com.egin.auth.model.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {

    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @NotNull(message = "User Type cannot be null")
    private UserType userType;

    private String department;

    private String position;

    private String phoneNumber;

    private LocalDateTime hireDate;

    private BigDecimal salary;

    private String storeId;

    private String branchId;
}

