package com.egin.shiftReport.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.order.model.mapper.ListOrderEntityToListOrderMapper;
import com.egin.product.model.mapper.product.ListProductEntityToListProductMapper;
import com.egin.refund.model.mapper.ListRefundEntityToListRefundWithoutShiftReportMapper;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

import java.util.Collections;

@UtilityClass
public class ShiftReportEntityToShiftReportMapper {

    public ShiftReport toShiftReport(final ShiftReportEntity entity) {
        if (entity == null) {
            return null;
        }

        return ShiftReport.builder()
                .id(entity.getId())
                .shiftStart(entity.getShiftStart())
                .shiftEnd(entity.getShiftEnd())
                .totalSales(entity.getTotalSales())
                .totalRefunds(entity.getTotalRefunds())
                .netSales(entity.getNetSales())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserEntityToUserMapper.toUser(entity.getCashier()))
                .branch(BranchEntityToBranchMapper.toBranch(entity.getBranchEntity()))
                .paymentSummaries(entity.getPaymentSummaries() != null ? entity.getPaymentSummaries() : Collections.emptyList())
                .topSellingProducts(entity.getTopSellingProducts() != null
                        ? ListProductEntityToListProductMapper.toListProduct(entity.getTopSellingProducts())
                        : Collections.emptyList())
                .recentOrders(entity.getRecentOrders() != null
                        ? ListOrderEntityToListOrderMapper.toOrderList(entity.getRecentOrders())
                        : Collections.emptyList())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

}

