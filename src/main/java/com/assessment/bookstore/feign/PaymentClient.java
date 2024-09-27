package com.assessment.bookstore.feign;

import com.assessment.bookstore.config.FeignConfig;
import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "quickteller-payment",
        url = "${spring.cloud.openfeign.client.config.checkout.base-url}",
        configuration = FeignConfig.class)
public interface PaymentClient {

    @PostMapping("${spring.cloud.openfeign.client.config.checkout.payment-url}")
    PaymentResponse pay(@RequestBody PaymentRequest paymentRequest);

}
