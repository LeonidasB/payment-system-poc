package com.intuit.paymentsystem.core.exceptionHandling.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ServerInternalException extends RuntimeException {

    public ServerInternalException(String message){
        super(message);
    }
}
