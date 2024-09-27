package com.assessment.bookstore.controller;

import com.assessment.bookstore.database.entity.Payment;
import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.dto.response.PaymentResponse;
import com.assessment.bookstore.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.assessment.bookstore.util.AppConstants.PAYMENT;

@Slf4j
@RestController
@RequestMapping(PAYMENT)
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public BaseStandardResponse<Payment> makePayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.payment(request);
    }

    @GetMapping("{userId}/history")
    public BaseCollectionResponse<Payment> paymentHistory(@PathVariable Long userId) {
        return paymentService.history(userId);
    }

}
