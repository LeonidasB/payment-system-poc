package com.intuit.paymentsystem.core.validator;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentException;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
public interface PaymentValidator {
    void validate(Payment payment) throws PaymentException;
}
