package com.intuit.paymentsystem.risk.engine.dao;

import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.risk.engine.dao.persistence.PaymentRepository;
import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.ExternalUserEntity;
import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.PaymentEntity;
import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.PaymentMethodEntity;
import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Dao to store payment record
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class PaymentDaoImpl implements PaymentDao {
    @Autowired
    private PaymentRepository repository;

    public String storePayment(ProcessedPayment payment, boolean accept){
        PaymentEntity entity = transformToEntity(payment, accept);
        log.info("Storing in entity DB: {}", entity);
        return repository.save(entity).getId();
    }

    private PaymentEntity transformToEntity(ProcessedPayment payment, boolean accept) {
        return new PaymentEntity(null, payment.getPaymentId().toString(), new UserEntity(payment.getUserId().toString()),
                new ExternalUserEntity(payment.getPayeeId().toString()), new PaymentMethodEntity(payment.getPaymentMethodId().toString()),
                payment.getAmount(), payment.getCurrency(), accept);
    }
}
