package com.egin.payment.model;

import com.egin.branch.model.Branch;
import com.egin.common.model.BaseModel;
import com.egin.order.model.Order;
import com.egin.order.model.enums.PaymentType;
import com.egin.payment.model.enums.PaymentStatus;
import com.egin.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Payment extends BaseModel {

    private String id;
    private String idempotencyKey;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private PaymentType paymentType;
    private Order order;
    private User cashier;
    private Branch branch;
    private String externalReferenceId;
    private String errorMessage;


}
