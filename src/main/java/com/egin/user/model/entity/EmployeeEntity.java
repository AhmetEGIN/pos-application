package com.egin.user.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.store.model.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {

    private String department;
    private String position;
    private String phoneNumber;
    private LocalDateTime hireDate;

    @Builder.Default
    private BigDecimal salary = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

}

