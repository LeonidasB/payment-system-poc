package com.intuit.paymentsystem.core.handler;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.ServerInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.UUID;

import static com.intuit.paymentsystem.api.Consts.QUEUE_NAME;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class PaymentHandler {
    @Autowired
    JmsTemplate jmsTemplate;

    private void sendMessage(final ProcessedPayment processedPayment) {
        log.info("sending payment to queue {} ", processedPayment);
        jmsTemplate.send(QUEUE_NAME, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(processedPayment);
            }
        });
    }

    public ProcessedPayment handle(Payment payment){
        ProcessedPayment processedPayment = new ProcessedPayment(payment, UUID.randomUUID());
        try{
            sendMessage(processedPayment);
        }catch (Exception e){
            log.error("Exception on send message to queue", e);
            throw new ServerInternalException("Exception on send payment to queue");
        }
        return processedPayment;
    }
}
