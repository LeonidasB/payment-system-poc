package com.intuit.paymentsystem.core.handler;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.ServerInternalException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class PaymentHandlerTest {

    private Payment payment = new Payment(10, "USD", UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"));
    private PaymentHandler testedClass;
    @Mock
    private JmsTemplate jmsTemplateMock;


    @Before
    public void init() {
        testedClass = new PaymentHandler(jmsTemplateMock);
    }

    @Test(expected = ServerInternalException.class)
    public void testExceptionOnSendToQueue() {
        doThrow(new UncategorizedJmsException("")).when(jmsTemplateMock).send(anyString(), any(MessageCreator.class));
        testedClass.handle(payment);
    }

    @Test
    public void testSendToQueue() {
        ProcessedPayment result = testedClass.handle(payment);
        assertNotNull("wrong result UUID", result.getPaymentId());
    }
}
