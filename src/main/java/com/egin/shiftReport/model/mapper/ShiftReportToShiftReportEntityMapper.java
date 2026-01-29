package com.egin.shiftReport.model.mapper;

import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShiftReportToShiftReportEntityMapper {

    public ShiftReportEntity toShiftReportEntity(final ShiftReport shiftReport) {
        if (shiftReport == null) {
            return null;
        }

        return ShiftReportEntity.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSales(shiftReport.getTotalSales())
                .totalRefunds(shiftReport.getTotalRefunds())
                .netSales(shiftReport.getNetSales())
                .totalOrders(shiftReport.getTotalOrders())
                .cashier(UserToUserEntityMapper.toUserEntity(shiftReport.getCashier()))
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(shiftReport.getBranch()))
                .paymentSummaries(shiftReport.getPaymentSummaries())
                .build();
    }

}
