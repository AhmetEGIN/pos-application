package com.egin.order.model.dto.request;

import com.egin.order.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    private String branchId;
    private String customerId;
    private PaymentType paymentType;
    private List<OrderItemCreateRequest> orderItems;

}

