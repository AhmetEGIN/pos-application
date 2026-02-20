package com.egin.payment.service.strategy;

import com.egin.order.model.enums.PaymentType;
import com.egin.payment.exception.PaymentProcessingException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PaymentType'a göre doğru ödeme stratejisini seçer
 */
@Component
public class PaymentStrategyFactory {

    private final Map<PaymentType, PaymentStrategy> strategyMap;


    public PaymentStrategyFactory(List<PaymentStrategy> strategies) {
        this.strategyMap = new HashMap<>();

        for (PaymentStrategy strategy : strategies) {
            strategyMap.put(strategy.getSupportedPaymentType(), strategy);
        }
    }


    /**
     * Verilen ödeme türüne göre uygun stratejiyi döndürür
     */
    public PaymentStrategy getStrategy(PaymentType paymentType) {
        PaymentStrategy strategy = strategyMap.get(paymentType);

        if (strategy == null) {
            throw new PaymentProcessingException(
                    "Unsupported payment type: " + paymentType +
                            ". Supported types: " + strategyMap.keySet());
        }

        return strategy;
    }

    /**
     * Desteklenen tüm ödeme türlerini döndürür
     */
    public List<PaymentType> getSupportedPaymentTypes() {
        return List.copyOf(strategyMap.keySet());
    }


}
