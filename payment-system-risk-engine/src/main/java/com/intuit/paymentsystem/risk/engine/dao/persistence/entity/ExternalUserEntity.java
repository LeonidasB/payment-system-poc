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
@Table(name = ("EXTERNAL_USER"), schema = ("intuit_payment"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = ("ID"), nullable = false, updatable = false)
    private String userID;
    @Column(name = ("NAME"), nullable = false)
    private String name;
    @Column(name = ("EMAIL"))
    private String email;
    @Column(name = ("DELETED"), nullable = false)
    private boolean deleted;
}