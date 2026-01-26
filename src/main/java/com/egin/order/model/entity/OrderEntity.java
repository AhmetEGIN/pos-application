package com.egin.order.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.common.model.entity.BaseEntity;
import com.egin.order.model.enums.OrderStatus;
import com.egin.order.model.enums.PaymentType;
import com.egin.user.model.entity.CustomerEntity;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private BranchEntity branchEntity;

    @ManyToOne
    private UserEntity cashier;

    @ManyToOne
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;

}
