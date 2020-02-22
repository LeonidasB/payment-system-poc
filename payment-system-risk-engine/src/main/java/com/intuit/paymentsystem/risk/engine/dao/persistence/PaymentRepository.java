package com.intuit.paymentsystem.risk.engine.dao.persistence;

import com.intuit.paymentsystem.risk.engine.dao.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author leonidb
 * @date 22/02/2020
 * @since {version}
 */

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

}