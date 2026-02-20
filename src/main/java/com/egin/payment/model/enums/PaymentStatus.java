package com.egin.payment.model.enums;

public enum PaymentStatus {

    CREATED,        // Ödeme oluşturuldu
    PROCESSING,     // Ödeme işleniyor
    PAID,           // Ödeme başarılı
    FAILED,         // Ödeme başarısız
    CANCELED,       // Ödeme iptal edildi
    REFUNDED        // Ödeme iade edildi
}
