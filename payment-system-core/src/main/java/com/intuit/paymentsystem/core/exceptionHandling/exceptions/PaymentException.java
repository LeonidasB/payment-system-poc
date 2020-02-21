package com.intuit.paymentsystem.core.exceptionHandling.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * base exception class to handle payment errors
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class PaymentException extends RuntimeException {

    public PaymentException(String message){
        super(message);
    }
}
