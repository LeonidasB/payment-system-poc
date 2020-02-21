package com.intuit.paymentsystem.risk.engine.persistence;

import lombok.*;

import javax.persistence.*;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Entity
@Table(name = ("User"))
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String userID;
    @Column(name = ("NAME"), nullable = false)
    private String name;
    @Column(name = ("DELETED"), nullable = false)
    private boolean deleted;
}
