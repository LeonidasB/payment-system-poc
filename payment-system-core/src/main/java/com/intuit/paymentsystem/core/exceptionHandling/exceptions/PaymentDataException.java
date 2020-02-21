package com.intuit.paymentsystem.core.exceptionHandling.exceptions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * payment method exception
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PaymentDataException extends PaymentException {
    public PaymentDataException(String message){
        super(message);
    }
}
