package io.pt.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.pt.payment.client.PaymentClient;
import io.pt.payment.dto.PaymentRequestDto;
import io.pt.payment.dto.PaymentResponseDto;
import io.pt.payment.model.ErrorModel;
import io.pt.payment.model.PaymentModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

    private final PaymentClient paymentClient;
    private final ObjectMapper objectMapper;

    public PaymentController(PaymentClient paymentClient, ObjectMapper objectMapper) {
        this.paymentClient = paymentClient;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/payment")
    public ModelAndView processPayment(@ModelAttribute PaymentModel paymentModel) {
        ModelAndView modelAndView;
        ResponseEntity<String> response = paymentClient.createPayment(
                PaymentRequestDto.buildRequest(paymentModel.getAmount()));
        if (response.getStatusCode().value() != 200) {
            modelAndView = new ModelAndView("error");
            ErrorModel errorModel = new ErrorModel();
            errorModel.setMessage(response.getBody());
            errorModel.setCode(response.getStatusCode().value());
            modelAndView.addObject("info", errorModel);
        } else {
            String url = map(response.getBody()).getResult().getRedirectUrl();
            modelAndView = new ModelAndView("redirect:" + url);
        }
        return modelAndView;
    }

    private PaymentResponseDto map(String response) {
        try {
            return objectMapper.readValue(response, PaymentResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
