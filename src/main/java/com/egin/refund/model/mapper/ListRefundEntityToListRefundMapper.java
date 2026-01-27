package com.egin.refund.model.mapper;

import com.egin.refund.model.Refund;
import com.egin.refund.model.entity.RefundEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ListRefundEntityToListRefundMapper {

    public List<Refund> toRefundList(final List<RefundEntity> refundEntities) {
        if (refundEntities == null) {
            return null;
        }

        return refundEntities.stream()
                .map(RefundEntityToRefundMapper::toRefund)
                .collect(Collectors.toList());
    }

}
