package com.egin.shiftReport.model.entity;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.common.model.entity.BaseEntity;
import com.egin.order.model.entity.OrderEntity;
import com.egin.product.model.entity.ProductEntity;
import com.egin.refund.model.entity.RefundEntity;
import com.egin.shiftReport.model.PaymentSummary;
import com.egin.user.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "shift_reports")
public class ShiftReportEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSales;
    private Double totalRefunds;
    private Double netSales;
    private Double totalOrders;

    @ManyToOne
    private UserEntity cashier;

    @ManyToOne
    private BranchEntity branchEntity;

    @Transient
    private List<PaymentSummary> paymentSummaries;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductEntity> topSellingProducts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderEntity> recentOrders;

    @OneToMany(mappedBy = "shiftReportEntity", cascade = CascadeType.ALL)
    private List<RefundEntity> refunds;


}
