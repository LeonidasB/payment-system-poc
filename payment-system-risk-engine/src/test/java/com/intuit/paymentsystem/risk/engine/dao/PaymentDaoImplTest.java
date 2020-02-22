package com.intuit.paymentsystem.risk.engine.dao;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.risk.engine.dao.persistence.PaymentRepository;
import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.PaymentEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class PaymentDaoImplTest {

    public static final String SOME_ID = "123";
    private ProcessedPayment payment = new ProcessedPayment(new Payment(10, "USD",UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1")), UUID.randomUUID());

    @Mock
    private PaymentRepository repositoryMock;
    @Mock
    private PaymentEntity entityMock;

    private PaymentDaoImpl testedClass;

    @Before
    public void init() {
        testedClass = new PaymentDaoImpl(repositoryMock);
    }

    @Test
    public void testStoreFlow() {
        when(repositoryMock.save(any(PaymentEntity.class))).thenReturn(entityMock);
        when(entityMock.getId()).thenReturn(SOME_ID);
        String result = testedClass.storePayment(payment, true);
        assertEquals("wrong ID returned", SOME_ID, result);
    }
}
