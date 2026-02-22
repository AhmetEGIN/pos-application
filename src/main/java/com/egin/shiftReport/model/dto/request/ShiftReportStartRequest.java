package com.egin.shiftReport.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReportStartRequest {

    @NotBlank(message = "Branch ID is required")
    private String branchId;

    @PastOrPresent(message = "Shift start time cannot be in the future")
    private LocalDateTime shiftStartTime;

}

