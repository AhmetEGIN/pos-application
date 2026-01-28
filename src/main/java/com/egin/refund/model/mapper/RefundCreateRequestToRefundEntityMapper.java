package com.egin.refund.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchToBranchEntityMapper;
import com.egin.order.model.Order;
import com.egin.order.model.mapper.OrderToOrderEntityMapper;
import com.egin.refund.model.dto.request.RefundCreateRequest;
import com.egin.refund.model.entity.RefundEntity;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.model.mapper.ShiftReportToShiftReportEntityMapper;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RefundCreateRequestToRefundEntityMapper {

    public RefundEntity toRefundEntity(
            final RefundCreateRequest request,
            final Order order,
            final Branch branch,
            final User cashier,
            final ShiftReport shiftReport
    ) {

        return RefundEntity.builder()
                .orderEntity(OrderToOrderEntityMapper.toOrderEntity(order))
                .reason(request.getReason())
                .amount(request.getAmount())
                .paymentType(request.getPaymentType())
                .shiftReportEntity(ShiftReportToShiftReportEntityMapper.toShiftReportEntity(shiftReport))
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .cashier(UserToUserEntityMapper.toUserEntity(cashier))
                .build();
    }

    public RefundEntity toRefundEntityWithShiftReportEntity(
            final RefundCreateRequest request,
            final Order order,
            final Branch branch,
            final User cashier,
            final ShiftReportEntity shiftReportEntity
    ) {

        return RefundEntity.builder()
                .orderEntity(OrderToOrderEntityMapper.toOrderEntity(order))
                .reason(request.getReason())
                .amount(request.getAmount())
                .paymentType(request.getPaymentType())
                .shiftReportEntity(shiftReportEntity)
                .branchEntity(BranchToBranchEntityMapper.toBranchEntity(branch))
                .cashier(UserToUserEntityMapper.toUserEntity(cashier))
                .build();
    }
}
