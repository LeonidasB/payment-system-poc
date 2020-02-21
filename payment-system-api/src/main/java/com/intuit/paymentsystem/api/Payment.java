package com.intuit.paymentsystem.api;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private double amount;
    private String currency;
    private UUID userId;
    private UUID payeeId;
    private UUID paymentMethodId;
}
