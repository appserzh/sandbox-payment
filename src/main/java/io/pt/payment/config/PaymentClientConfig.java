package io.pt.payment.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import io.pt.payment.client.NoopException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Configuration
public class PaymentClientConfig {

    @Value("${sandbox-payment-client.token}")
    private String token;

    @Bean
    public RequestInterceptor bearerInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + token);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            int status = response.status();
            String body = "";
            try {
                body = new String(StreamUtils.copyToByteArray(response.body().asInputStream()));
            } catch (IOException ignore) {
            }
            return new NoopException(status, body);
        };
    }
}
