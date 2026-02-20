package com.egin.payment.repository;

import com.egin.payment.model.entity.PaymentEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM PaymentEntity p WHERE p.idempotencyKey = :idempotencyKey")
    Optional<PaymentEntity> findByIdempotencyKeyForUpdate(String idempotencyKey);


    Optional<PaymentEntity> findByIdempotencyKey(String idempotencyKey);

    Page<PaymentEntity> findByOrderEntityId(String orderId, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PaymentEntity p WHERE p.orderEntity.id = :orderId AND p.paymentStatus = 'PAID'")
    boolean existsPaidPaymentByOrderId(String orderId);
}
