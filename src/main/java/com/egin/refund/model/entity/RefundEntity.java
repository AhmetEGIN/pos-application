package com.egin.refund.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.common.model.entity.BaseEntity;
import com.egin.order.model.entity.OrderEntity;
import com.egin.order.model.enums.PaymentType;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "refunds")
public class RefundEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private OrderEntity orderEntity;

    private String reason;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

//    @ManyToOne
//    @JsonIgnore
//    private ShiftReportEntity shiftReportEntity;

    @ManyToOne
    private UserEntity cashier;

    @ManyToOne
    private BranchEntity branchEntity;

}
