package com.intuit.paymentsystem.core.validator;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentDataException;
import com.intuit.paymentsystem.core.management.client.PayeeClient;
import com.intuit.paymentsystem.core.management.client.PaymentMethodClient;
import com.intuit.paymentsystem.core.management.client.UserClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class PaymentValidatorImplTest {
    private Payment payment = new Payment(10, "USD", UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"));
    @Mock
    private UserClient userClientMock;
    @Mock
    private PayeeClient payeeClientMock;
    @Mock
    private PaymentMethodClient paymentMethodClientMock;

    private PaymentValidatorImpl testedClass;

    @Before
    public void init() {
        testedClass = new PaymentValidatorImpl(userClientMock, payeeClientMock, paymentMethodClientMock);
    }


    @Test(expected = PaymentDataException.class)
    public void testWrongUserID() {
       when(userClientMock.IsUserExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(false);
       testedClass.validate(payment);
    }

    @Test(expected = PaymentDataException.class)
    public void testWrongPayeeID() {
        when(userClientMock.IsUserExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(true);
        when(payeeClientMock.IsPayeeExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(false);
        testedClass.validate(payment);
    }

    @Test(expected = PaymentDataException.class)
    public void testWrongPaymentMethodID() {
        when(userClientMock.IsUserExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(true);
        when(payeeClientMock.IsPayeeExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(true);
        when(paymentMethodClientMock.IsPaymentMethodExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"))).thenReturn(false);
        testedClass.validate(payment);
    }
}
