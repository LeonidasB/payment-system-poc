package com.intuit.paymentsystem.risk.engine.dao;

import com.intuit.paymentsystem.api.ProcessedPayment;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
public interface PaymentDao {
    String storePayment(ProcessedPayment payment, boolean accept);
}
