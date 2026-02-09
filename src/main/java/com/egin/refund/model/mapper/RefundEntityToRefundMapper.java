package com.egin.refund.model.mapper;

import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.order.model.mapper.OrderEntityToOrderMapper;
import com.egin.refund.model.Refund;
import com.egin.refund.model.entity.RefundEntity;
import com.egin.shiftReport.model.mapper.ShiftReportEntityToShiftReportMapper;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RefundEntityToRefundMapper {

    public Refund toRefund(final RefundEntity refundEntity) {
        if (refundEntity == null) {
            return null;
        }


        return Refund.builder()
                .id(refundEntity.getId())
                .order(OrderEntityToOrderMapper.toOrder(refundEntity.getOrderEntity()))
                .reason(refundEntity.getReason())
                .amount(refundEntity.getAmount())
                .paymentType(refundEntity.getPaymentType())
                .shiftReport(ShiftReportEntityToShiftReportMapper.toShiftReport(refundEntity.getShiftReportEntity()))
                .cashier(UserEntityToUserMapper.toUser(refundEntity.getCashier()))
                .branch(BranchEntityToBranchMapper.toBranch(refundEntity.getBranchEntity()))
                .createdAt(refundEntity.getCreatedAt())
                .updatedAt(refundEntity.getUpdatedAt())
                .createdBy(refundEntity.getCreatedBy())
                .updatedBy(refundEntity.getUpdatedBy())
                .build();
    }

}
