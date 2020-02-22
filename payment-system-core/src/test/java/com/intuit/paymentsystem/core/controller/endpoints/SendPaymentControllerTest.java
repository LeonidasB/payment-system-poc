package com.intuit.paymentsystem.core.controller.endpoints;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentDataException;
import com.intuit.paymentsystem.core.handler.PaymentHandler;
import com.intuit.paymentsystem.core.validator.PaymentValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class SendPaymentControllerTest {
    private Payment payment = new Payment(10, "USD", UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"));
    private SendPaymentController testedClass;
    @Mock
    private PaymentHandler paymentHandlerMock;
    @Mock
    private PaymentValidator paymentValidatorMock;

    @Before
    public void init() {
        testedClass = new SendPaymentController(paymentHandlerMock, paymentValidatorMock);
    }

    @Test(expected = PaymentDataException.class)
    public void testWrongUserID() {
        doThrow(new PaymentDataException("wrong user id")).when(paymentValidatorMock).validate(payment);
        testedClass.handlePayment(payment);
    }

    @Test
    public void testCorrectSendToQueue() {
        ProcessedPayment processedPayment = new ProcessedPayment(payment, UUID.randomUUID());
        when(paymentHandlerMock.handle(payment)).thenReturn(processedPayment);
        ResponseEntity<ProcessedPayment> result = testedClass.handlePayment(payment);
        assertEquals("wrong response code", HttpStatus.CREATED, result.getStatusCode());
        assertEquals("wrong body", processedPayment, result.getBody());
    }
}
