package com.egin.shiftReport.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReportEndRequest {

    @NotBlank(message = "Shift Report ID is required")
    private String shiftReportId;

    private LocalDateTime shiftEndTime;

}

