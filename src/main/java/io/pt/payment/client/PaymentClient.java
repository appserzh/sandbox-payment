package io.pt.payment.client;

import io.pt.payment.config.PaymentClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sandbox-payment-client",
        url = "${sandbox-payment-client.url}",
        path = "${sandbox-payment-client.path}",

        configuration = PaymentClientConfig.class)
public interface PaymentClient {
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createPaymentEx(@RequestBody String paymentRequestDto);

    default ResponseEntity<String> createPayment(String paymentRequestDto) {
        try {
            return createPaymentEx(paymentRequestDto);
        } catch (NoopException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }
}
