package com.egin.branch.model.request;

import com.egin.common.validation.ValidWorkingHours;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ValidWorkingHours
public class BranchCreateRequest {

    @NotBlank(message = "Branch name cannot be blank")
    @Size(min = 2, max = 100, message = "Branch name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 10, max = 500, message = "Address must be between 10 and 500 characters")
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Please enter a valid phone number (E.164 format)")
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter a valid email address")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotEmpty(message = "Working days cannot be empty")
    @Size(min = 1, max = 7, message = "Working days must be between 1 and 7")
    private List<String> workingDays;

    @NotNull(message = "Open time is required")
    private LocalDateTime openTime;

    @NotNull(message = "Close time is required")
    private LocalDateTime closeTime;

    @NotBlank(message = "Store ID is required")
    private String storeEntityId;

    @NotBlank(message = "User ID is required")
    private String userEntityId;

}
