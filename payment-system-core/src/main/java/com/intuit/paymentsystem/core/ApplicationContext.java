package com.intuit.paymentsystem.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.guava.GuavaCache;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Configuration
@EnableCaching
@EnableJms
public class ApplicationContext {

    public static final String USER_CACHE_NAME = "userCache";
    public static final String PAYEE_CACHE_NAME = "payeeCache";
    public static final String PAYMENT_METHOD_CACHE_NAME = "paymentMethodCache";

    @Value("${dictionary.cache.ttl.in.minutes}")
    private long cacheTtl;

    @Value("${jms.broker.url}")
    private String brokerUrl;
    @Value("${jms.broker.username}")
    private String brokerUsername;
    @Value("${jms.broker.pass}")
    private String brokerPassword;

    private Cache createCache() {
        return CacheBuilder.newBuilder().recordStats().expireAfterWrite(cacheTtl, TimeUnit.MINUTES).build();
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setPassword(brokerUsername);
        connectionFactory.setUserName(brokerPassword);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }

    @Bean
    public CacheManager dictionaryCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Lists.newArrayList(new GuavaCache(USER_CACHE_NAME, createCache()), new GuavaCache(PAYMENT_METHOD_CACHE_NAME, createCache()), new GuavaCache(PAYEE_CACHE_NAME, createCache())));
        return cacheManager;
    }
}
