package com.intuit.paymentsystem.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ProcessedPayment extends Payment {
    private UUID paymentId;

    public ProcessedPayment(double amount, String currency, UUID userId, UUID payeeId, UUID paymentMethodId) {
        super(amount, currency, userId, payeeId, paymentMethodId);
    }

    public ProcessedPayment(Payment payment, UUID paymentId){
        super(payment.getAmount(), payment.getCurrency(), payment.getUserId(), payment.getPayeeId(), payment.getPaymentMethodId());
        this.paymentId = paymentId;
    }
}