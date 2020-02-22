package com.intuit.paymentsystem.risk.engine.handler;

import com.intuit.paymentsystem.api.ProcessedPayment;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
public interface RiskManager{

    boolean acceptPayment(ProcessedPayment payment);
}
