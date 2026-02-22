package com.egin.order.model.dto.request;

import com.egin.common.validation.SufficientStock;
import com.egin.order.model.enums.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SufficientStock
public class OrderCreateRequest {

    @NotBlank(message = "Branch ID cannot be blank")
    private String branchId;

    private String customerId;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotEmpty(message = "Order must contain at least one item")
    @Size(min = 1, max = 100, message = "Order can contain 1-100 items")
    @Valid
    private List<OrderItemCreateRequest> orderItems;

}

