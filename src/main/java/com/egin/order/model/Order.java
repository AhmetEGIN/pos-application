package com.egin.order.model;

import com.egin.branch.model.Branch;
import com.egin.common.model.BaseModel;
import com.egin.order.model.enums.OrderStatus;
import com.egin.order.model.enums.PaymentType;
import com.egin.user.model.Customer;
import com.egin.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Order extends BaseModel {

    private String id;
    private Double totalAmount;
    private PaymentType paymentType;
    private OrderStatus orderStatus;
    private Branch branch;
    private User cashier;
    private Customer customer;
    private List<OrderItem> orderItems;

}
