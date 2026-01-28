package com.egin.refund.model;

import com.egin.branch.model.Branch;
import com.egin.common.model.BaseModel;
import com.egin.order.model.Order;
import com.egin.order.model.enums.PaymentType;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Refund extends BaseModel {

    private String id;
    private Order order;
    private String reason;
    private Double amount;
    private PaymentType paymentType;
    private ShiftReport shiftReport;
    private User cashier;
    private Branch branch;

}
