package com.intuit.paymentsystem.core.controller.endpoints;


import com.intuit.paymentsystem.api.Payment;
import com.intuit.paymentsystem.api.ProcessedPayment;
import com.intuit.paymentsystem.core.handler.PaymentHandler;
import com.intuit.paymentsystem.core.validator.PaymentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Send payment controller
 * @author leonidb
 * @date 21/02/2020
 * @since {version}
 */

@Slf4j
@RestController
public class SendPaymentController extends BaseRestApiController{

    private PaymentHandler paymentHandler;
    private PaymentValidator paymentValidator;

    @Autowired
    public SendPaymentController(PaymentHandler paymentHandler,PaymentValidator paymentValidator){
        this.paymentHandler = paymentHandler;
        this.paymentValidator = paymentValidator;
    }

    @PostMapping(value = API.SEND_PAYMENT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProcessedPayment> handlePayment(@RequestBody Payment payment) {
        log.info("call of handlePayment");
        paymentValidator.validate(payment);
        ProcessedPayment result = paymentHandler.handle(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

