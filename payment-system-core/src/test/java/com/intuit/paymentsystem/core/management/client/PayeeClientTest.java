package com.intuit.paymentsystem.core.management.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@RunWith(PowerMockRunner.class)
public class PayeeClientTest {
    private PayeeClient testedClass = new PayeeClient();

    @Test
    public void testWrongPayeeID() {
        assertFalse("wrong result" , testedClass.IsPayeeExist(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa2d1")));
    }

    @Test
    public void testCorrectPayeeID() {
        assertTrue("wrong result" , testedClass.IsPayeeExist(UUID.fromString("e7bdca08-056f-11e9-ade9-2dce83242c71")));
    }
}
