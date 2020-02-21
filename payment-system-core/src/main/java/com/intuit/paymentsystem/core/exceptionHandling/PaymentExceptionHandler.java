package com.intuit.paymentsystem.core.exceptionHandling;

import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentDataException;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.ServerInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * general handler to catch custom exceptions
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Slf4j
@ControllerAdvice
public class PaymentExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PaymentDataException.class)
    @ResponseBody
    public Object handleIllegalArgument(PaymentDataException e, HttpServletRequest request) {
        log.error("PaymentDataException was thrown: " + e.getMessage(), e);
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerInternalException.class)
    @ResponseBody
    public Object handleServerException(ServerInternalException e, HttpServletRequest request) {
        log.error("ServerInternalException was thrown: " + e.getMessage(), e);
        return e.getMessage();
    }

}