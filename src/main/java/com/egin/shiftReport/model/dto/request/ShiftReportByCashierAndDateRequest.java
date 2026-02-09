package com.egin.shiftReport.model.dto.request;

import com.egin.common.model.dto.request.CustomPagingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReportByCashierAndDateRequest extends CustomPagingRequest {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}

