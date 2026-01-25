package com.egin.user.model;

import com.egin.branch.model.Branch;
import com.egin.store.model.Store;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Employee extends User {

    private String department;
    private String position;
    private String phoneNumber;
    private LocalDateTime hireDate;

    @Builder.Default
    private BigDecimal salary = BigDecimal.ZERO;

    private Store store;
    private Branch branch;

}
