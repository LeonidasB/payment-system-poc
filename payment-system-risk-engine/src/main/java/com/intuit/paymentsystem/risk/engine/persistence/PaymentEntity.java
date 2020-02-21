package com.intuit.paymentsystem.risk.engine.persistence;

import lombok.*;

import javax.persistence.*;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Entity
@Table(name = ("Payment"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PAYMENT_REQUEST_ID")
    private Integer paymentRequestID;

    @JoinColumn(name = "USER_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @JoinColumn(name = "PAYEE_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private ExternalUserEntity payee;

    @JoinColumn(name = "PAYMENTQ_METHOD_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethodEntity paymentMethod;

    @Column(name = ("AMOUNT"), nullable = false)
    private double amount;
    @Column(name = ("CURRENCY"), nullable = false)
    private String currency;
}
