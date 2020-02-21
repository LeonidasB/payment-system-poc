package com.intuit.paymentsystem.core.validator;

import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentDataException;
import com.intuit.paymentsystem.core.exceptionHandling.exceptions.PaymentException;
import com.intuit.paymentsystem.core.management.client.PayeeClient;
import com.intuit.paymentsystem.core.management.client.PaymentMethodClient;
import com.intuit.paymentsystem.core.management.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */
@Component
@Slf4j
public class PaymentValidatorImpl implements PaymentValidator {

    private static final String CURRENCY_REGEX = "(EUR|GBP|USD)";
    public static final String PROVIDED_INCORRECT_PAYMENT_METHOD = "Provided incorrect payment method";
    public static final String PROVIDED_INCORRECT_AMOUNT = "Provided incorrect amount";
    public static final String PROVIDED_INCORRECT_CURRENCY = "Provided incorrect currency";
    public static final String PROVIDED_INCORRECT_PAYEE_ID = "Provided incorrect payeeID";
    public static final String PROVIDED_INCORRECT_USER_ID = "Provided incorrect userId";

    private UserClient userClient;
    private PayeeClient payeeClient;
    private PaymentMethodClient paymentMethodClient;

    @Autowired
    public PaymentValidatorImpl(UserClient userClient, PayeeClient payeeClient, PaymentMethodClient paymentMethodClient){
        this.userClient = userClient;
        this.payeeClient = payeeClient;
        this.paymentMethodClient = paymentMethodClient;
    }

    private void validateCurrency(String currency) {
        if(!Pattern.matches(CURRENCY_REGEX, currency)){
            throw new PaymentDataException(PROVIDED_INCORRECT_CURRENCY);
        }
    }

    private void validateAmount(double amount) {
        if(amount <= 0){
            throw new PaymentDataException(PROVIDED_INCORRECT_AMOUNT);
        }
    }

    private void validatePaymentMethod(UUID paymentMethod) {
        if(!paymentMethodClient.IsPaymentMethodExist(paymentMethod)){
            throw new PaymentDataException(PROVIDED_INCORRECT_PAYMENT_METHOD);
        }
    }

    private void validatePayee(UUID payee) {
        if(!payeeClient.IsPayeeExist(payee)){
            throw new PaymentDataException(PROVIDED_INCORRECT_PAYEE_ID);
        }
    }

    private void validateUser(UUID userId) {
        if(!userClient.IsUserExist(userId)){
            throw new PaymentDataException(PROVIDED_INCORRECT_USER_ID);
        }
    }

    public void validate(Payment payment) throws PaymentException {
        log.info("Payment validation: {}", payment);
        validateUser(payment.getUserId());
        validatePayee(payment.getPayeeId());
        validatePaymentMethod(payment.getPaymentMethodId());
        validateAmount(payment.getAmount());
        validateCurrency(payment.getCurrency());
    }


}
