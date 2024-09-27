package com.assessment.bookstore.controller;

import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.dto.response.PaymentResponse;
import com.assessment.bookstore.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.assessment.bookstore.util.AppConstants.PAYMENT;

@Slf4j
@RestController
@RequestMapping(PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public BaseStandardResponse<PaymentResponse> makePayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.payment(request);
    }

}
