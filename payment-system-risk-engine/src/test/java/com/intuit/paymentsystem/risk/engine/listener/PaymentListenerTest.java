package com.intuit.paymentsystem.risk.engine.listener;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.risk.engine.dao.PaymentDao;
import com.intuit.paymentsystem.risk.engine.handler.RiskManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.jms.ObjectMessage;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class PaymentListenerTest {
    public static final String SOME_ID = "123";
    private ProcessedPayment payment = new ProcessedPayment(new Payment(10, "USD", UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1")), UUID.randomUUID());

    @Mock
    private PaymentDao paymentDaoMock;
    @Mock
    private RiskManager riskManagerMock;
    @Mock
    private ObjectMessage inputObjectMock;
    private PaymentListener testedClass;


    @Before
    public void init() {
        testedClass = new PaymentListener(riskManagerMock, paymentDaoMock);
    }


    @Test
    public void testGetMessageFlow() throws Exception {
        when(inputObjectMock.getObject()).thenReturn(payment);
        testedClass.receiveMessageFromTopic(inputObjectMock);
        verify(riskManagerMock, times(1)).acceptPayment(payment);
        verify(paymentDaoMock, times(1)).storePayment(payment, false);
    }

    @Test
    public void testGetMessageFailureFlow() throws Exception {
        when(inputObjectMock.getObject()).thenReturn(new String());
        testedClass.receiveMessageFromTopic(inputObjectMock);
        verify(riskManagerMock, never()).acceptPayment(any(ProcessedPayment.class));
        verify(paymentDaoMock, never()).storePayment(any(ProcessedPayment.class), anyBoolean());

    }
}