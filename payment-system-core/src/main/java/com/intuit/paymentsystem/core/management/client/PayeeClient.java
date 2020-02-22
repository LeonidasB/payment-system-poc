package com.intuit.paymentsystem.core.management.client;

import com.intuit.paymentsystem.core.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * mock client to validate external user exist
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class PayeeClient {
    private final static List<UUID> payees = Arrays.asList(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("e7bdca08-056f-11e9-ade9-2dce83242c71"));

    @Cacheable(ApplicationContext.PAYEE_CACHE_NAME)
    public boolean IsPayeeExist(UUID userId){
        return payees.contains(userId);
    }
}
