package com.egin.user.model.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeUpdateRequest {

    private String firstName;

    private String lastName;

    private String department;

    private String position;

    private String phoneNumber;

    private LocalDateTime hireDate;

    private BigDecimal salary;
}

