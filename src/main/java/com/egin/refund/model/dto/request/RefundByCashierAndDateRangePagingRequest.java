package com.egin.refund.model.dto.request;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RefundByCashierAndDateRangePagingRequest extends CustomPagingRequest {

    private String cashierId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
