package com.egin.payment.model.dto.request;

import com.egin.order.model.enums.PaymentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentCreateRequest {

    @NotBlank(message = "Order ID is required")
    private String orderId;

    @NotBlank(message = "Branch ID is required")
    private String branchId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @Builder.Default
    private String currency = "TRY";

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

}
