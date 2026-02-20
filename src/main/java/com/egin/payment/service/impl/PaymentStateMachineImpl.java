package com.egin.payment.service.impl;

import com.egin.payment.exception.IllegalPaymentStateTransitionException;
import com.egin.payment.model.enums.PaymentStatus;
import com.egin.payment.service.PaymentStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Payment State Machine Implementation
 *
 * İzin verilen geçişler:
 * - CREATED → PROCESSING (ödeme başlatıldı)
 * - PROCESSING → PAID (ödeme başarılı)
 * - PROCESSING → FAILED (ödeme başarısız)
 * - PAID → REFUNDED (iade edildi)
 * - PAID → CANCELED (iptal edildi)
 * - FAILED → CREATED (tekrar deneme için)
 */
@Component
public class PaymentStateMachineImpl implements PaymentStateMachine {

    private static final Map<PaymentStatus, Set<PaymentStatus>> ALLOWED_TRANSITIONS;

    static {
        Map<PaymentStatus, Set<PaymentStatus>> transitions = new EnumMap<>(PaymentStatus.class);

        // CREATED → PROCESSING
        transitions.put(PaymentStatus.CREATED, EnumSet.of(PaymentStatus.PROCESSING));

        // PROCESSING → PAID veya FAILED
        transitions.put(PaymentStatus.PROCESSING, EnumSet.of(PaymentStatus.PAID, PaymentStatus.FAILED));

        // PAID → REFUNDED veya CANCELED
        transitions.put(PaymentStatus.PAID, EnumSet.of(PaymentStatus.REFUNDED, PaymentStatus.CANCELED));

        // FAILED → CREATED (retry mekanizması için)
        transitions.put(PaymentStatus.FAILED, EnumSet.of(PaymentStatus.CREATED));

        // CANCELED ve REFUNDED terminal durumlar - geçiş yok
        transitions.put(PaymentStatus.CANCELED, EnumSet.noneOf(PaymentStatus.class));
        transitions.put(PaymentStatus.REFUNDED, EnumSet.noneOf(PaymentStatus.class));

        ALLOWED_TRANSITIONS = Collections.unmodifiableMap(transitions);
    }


    @Override
    public boolean isValidTransition(PaymentStatus from, PaymentStatus to) {
        Set<PaymentStatus> allowedTargets = ALLOWED_TRANSITIONS.get(from);
        return allowedTargets != null && allowedTargets.contains(to);
    }

    @Override
    public void validateTransition(PaymentStatus from, PaymentStatus to) {
        if (!isValidTransition(from, to)) {
            throw new IllegalPaymentStateTransitionException(from, to);
        }
    }

    @Override
    public Set<PaymentStatus> getAllowedTransitions(PaymentStatus from) {
        return ALLOWED_TRANSITIONS.getOrDefault(from, EnumSet.noneOf(PaymentStatus.class));
    }

    @Override
    public Map<PaymentStatus, Set<PaymentStatus>> getAllTransitionRules() {
        return ALLOWED_TRANSITIONS;
    }
}
