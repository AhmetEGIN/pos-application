package com.egin.shiftReport.model;

import com.egin.branch.model.Branch;
import com.egin.common.model.BaseModel;
import com.egin.order.model.Order;
import com.egin.product.model.Product;
import com.egin.refund.model.Refund;
import com.egin.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShiftReport extends BaseModel {

    private String id;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private Double totalSales;
    private Double totalRefunds;
    private Double netSales;
    private Double totalOrders;
    private User cashier;
    private Branch branch;
    private List<PaymentSummary> paymentSummaries;
    private List<Product> topSellingProducts;
    private List<Order> recentOrders;
    private List<Refund> refunds;

}

