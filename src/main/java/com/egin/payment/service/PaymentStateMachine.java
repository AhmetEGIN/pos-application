package com.egin.payment.service;

import com.egin.payment.model.enums.PaymentStatus;

import java.util.Map;
import java.util.Set;

/**
 * Payment State Machine - izin verilen durum geçişlerini yönetir
 *
 * State Diagram:
 *
 *  CREATED ───────► PROCESSING ────────► PAID ────────► REFUNDED
 *                         │                  │
 *                         │                  └──────────► CANCELED
 *                         │
 *                         └──────────► FAILED
 *                                         │
 *                                         └────► CREATED (retry)
 */
public interface PaymentStateMachine {

    boolean isValidTransition(final PaymentStatus from, final PaymentStatus to);

    void validateTransition(final PaymentStatus from, final PaymentStatus to);

    Set<PaymentStatus> getAllowedTransitions(final PaymentStatus from);

    Map<PaymentStatus, Set<PaymentStatus>> getAllTransitionRules();


}
