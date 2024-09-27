package io.pt.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SandboxPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxPaymentApplication.class, args);
	}

}
