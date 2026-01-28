package com.egin.shiftReport.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ShiftReportEntityFactory {

    private static final Double INITIAL_SALES = 0.0;
    private static final Double INITIAL_REFUNDS = 0.0;
    private static final Double INITIAL_NET_SALES = 0.0;
    private static final Double INITIAL_ORDERS = 0.0;

    public ShiftReportEntity createForNewShift(Branch branch, User cashier, LocalDateTime shiftStartTime) {
        return ShiftReportEntity.builder()
                .shiftStart(shiftStartTime != null ? shiftStartTime : LocalDateTime.now())
                .totalSales(INITIAL_SALES)
                .totalRefunds(INITIAL_REFUNDS)
                .netSales(INITIAL_NET_SALES)
                .totalOrders(INITIAL_ORDERS)
                .cashier(UserToUserEntityMapper.toUserEntity(cashier))
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .build();
    }

}

