package com.egin.payment.model.mapper;

import com.egin.payment.model.Payment;
import com.egin.payment.model.entity.PaymentEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListPaymentEntityToPaymentMapper {


    public static List<Payment> toPaymentList(List<PaymentEntity> content) {
        return content.stream()
                .map(PaymentEntityToPaymentMapper::toPayment)
                .toList();
    }
}
