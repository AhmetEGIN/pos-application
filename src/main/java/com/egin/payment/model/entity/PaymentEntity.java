package com.egin.payment.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.common.model.entity.BaseEntity;
import com.egin.order.model.entity.OrderEntity;
import com.egin.order.model.enums.PaymentType;
import com.egin.payment.model.enums.PaymentStatus;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments", indexes = {
        @Index(name = "idx_payment_idempotency_key", columnList = "idempotencyKey", unique = true),
        @Index(name = "idx_payment_order_id", columnList = "order_entity_id"),
        @Index(name = "idx_payment_status", columnList = "status")
})
public class PaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //Aynı ödeme isteğinin tekrar işlenmesini önler. Frontend'den UUID olarak gönderilir
    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_entity_id")
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id")
    private UserEntity cashier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_entity_id")
    private BranchEntity branchEntity;

    private String errorMessage;

    // Dış sistemlerdeki referanslar için kullanılabilir (örneğin, ödeme sağlayıcılarının işlem ID'leri)
    private String externalReferenceId;

    // Optimistic locking için versiyon
    @Version
    private Long version;

}
