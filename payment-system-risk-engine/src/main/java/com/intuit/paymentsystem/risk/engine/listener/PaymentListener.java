package com.intuit.paymentsystem.risk.engine.listener;

import com.intuit.paymentsystem.api.ProcessedPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import static com.intuit.paymentsystem.api.Consts.QUEUE_NAME;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Controller
@Slf4j
public class PaymentListener {

    @JmsListener(destination = QUEUE_NAME)
    public void receiveMessageFromTopic(final Message paymentMessage) throws JMSException {
        log.info("Received message " + paymentMessage);

        if(paymentMessage instanceof ObjectMessage) {
            ProcessedPayment payment = (ProcessedPayment)((ObjectMessage)paymentMessage).getObject();
        }
    }

}
