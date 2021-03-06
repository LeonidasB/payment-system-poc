package com.intuit.paymentsystem.risk.engine.dao.persistence.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Entity
@Table(name = ("PAYMENT"), schema = ("intuit_payment"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = ("ID"), nullable = false, updatable = false)
    private String id;
    @Column(name = ("PAYMENT_REQUEST_ID"), nullable = false)
    private String paymentRequestID;
    @ManyToOne
    @JoinColumn(name = ("USER_ID"), referencedColumnName = ("ID"))
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = ("PAYEE_ID"), referencedColumnName = ("ID"))
    private ExternalUserEntity payee;
    @ManyToOne
    @JoinColumn(name = ("PAYMENT_METHOD_ID"), referencedColumnName = ("ID"))
    private PaymentMethodEntity paymentMethod;
    @Column(name = ("AMOUNT"), nullable = false)
    private double amount;
    @Column(name = ("CURRENCY"), nullable = false)
    private String currency;
    @Column(name = ("SUCCEEDED"), nullable = false)
    private Boolean succeeded;
}
