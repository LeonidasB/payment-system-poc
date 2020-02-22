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
@Table(name = ("PAYMENT_METHOD"), schema = ("intuit_payment"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = ("ID"), nullable = false, updatable = false)
    private String methodID;
    @Column(name = ("NAME"), nullable = false)
    private String name;
    @Column(name = ("DELETED"), nullable = false)
    private boolean deleted;
}