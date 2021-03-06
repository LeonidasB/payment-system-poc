package com.intuit.paymentsystem.risk.engine.listener;

import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.risk.engine.dao.PaymentDao;
import com.intuit.paymentsystem.risk.engine.handler.RiskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.ObjectMessage;

import static com.intuit.paymentsystem.api.Consts.QUEUE_NAME;

/**
 * Queue listener
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class PaymentListener {
    private RiskManager riskManager;
    private PaymentDao paymentDao;

    @Autowired
    public PaymentListener(RiskManager riskManager, PaymentDao paymentDao){
        this.riskManager = riskManager;
        this.paymentDao = paymentDao;
    }

    @JmsListener(destination = QUEUE_NAME)
    public void receiveMessageFromTopic(final Message paymentMessage){
        log.info("Received message " + paymentMessage);

        if(paymentMessage instanceof ObjectMessage) {
            try {
                ProcessedPayment payment = (ProcessedPayment) ((ObjectMessage) paymentMessage).getObject();
                boolean accepted = riskManager.acceptPayment(payment);
                paymentDao.storePayment(payment, accepted);
            }catch (Exception e){
                log.error("Exception on processing messagfe from queue", e);
                //handle somehow invalid message. For example store to another queue
            }
        }
    }

}
