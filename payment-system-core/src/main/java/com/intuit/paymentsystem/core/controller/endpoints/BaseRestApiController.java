package com.intuit.paymentsystem.core.controller.endpoints;


import lombok.extern.slf4j.Slf4j;

/**
 * base controller for rest API
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Slf4j
public abstract class BaseRestApiController {

    public class API {
        public static final String API_ROOT = "/payment/api/v0/";
        public static final String SEND_PAYMENT = API_ROOT + "sendPayment";
    }

}
