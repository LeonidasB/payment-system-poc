package com.intuit.paymentsystem.core.management.client;

import com.intuit.paymentsystem.core.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class UserClient {

    private final static List<UUID> users = Arrays.asList(UUID.fromString("ebbd8b9d-a065-4752-b545-e0103d7aa1d1"), UUID.fromString("e7bdca08-056f-11e9-ade9-2dce83242c71"));

    @Cacheable(ApplicationContext.USER_CACHE_NAME)
    public boolean IsUserExist(UUID userId){
        return users.contains(userId);
    }
}
