package com.intuit.paymentsystem.risk.engine.persistence;

import lombok.*;

import javax.persistence.*;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Entity
@Table(name = ("PaymentMethod"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String methodID;
    @Column(name = ("NAME"), nullable = false)
    private String name;
    @Column(name = ("DELETED"), nullable = false)
    private boolean deleted;
}