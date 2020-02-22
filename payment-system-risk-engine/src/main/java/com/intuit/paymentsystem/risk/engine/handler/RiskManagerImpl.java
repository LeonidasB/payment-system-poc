package com.intuit.paymentsystem.risk.engine.handler;

import com.intuit.paymentsystem.api.ProcessedPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class RiskManagerImpl implements RiskManager{
    private static final double MAX_REJECTED_RISK_RATE = 3.0;

    public boolean acceptPayment(ProcessedPayment payment){
        log.info("Analizing payment risk: {}", payment);
        double riskScore = evaluateRisk(payment);
        return  riskScore > MAX_REJECTED_RISK_RATE;

    }

    protected double evaluateRisk(ProcessedPayment payment){
        return Math.random() * 9 + 1;
    }
}
