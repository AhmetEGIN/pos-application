package com.egin.refund.model.dto.request;

import com.egin.order.model.enums.PaymentType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundCreateRequest {

    @NotBlank(message = "Order ID cannot be blank")
    private String orderId;

    @NotBlank(message = "Branch ID cannot be blank")
    private String branchId;

    @NotBlank(message = "Refund reason is required")
    @Size(min = 10, max = 500, message = "Reason must be between 10 and 500 characters")
    private String reason;

    @NotNull(message = "Refund amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Refund amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format")
    private Double amount;

    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @NotBlank(message = "Shift report ID is required")
    private String shiftReportId;

}
