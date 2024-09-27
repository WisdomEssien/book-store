package com.assessment.bookstore.service;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.database.entity.Cart;
import com.assessment.bookstore.database.repository.CartRepository;
import com.assessment.bookstore.dto.request.CartRequest;
import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.request.StockRequest;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.dto.response.PaymentResponse;
import com.assessment.bookstore.exception.BookStoreException;
import com.assessment.bookstore.feign.PaymentClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.assessment.bookstore.util.ResponseCode.*;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CartService cartService;
    private final PaymentClient paymentClient;

    public BaseStandardResponse<PaymentResponse> payment(PaymentRequest request) {
        PaymentResponse response = paymentClient.pay(request);
        if (response.isPaymentComleted()) {
            log.info("Payment successful");
            cartService.deleteCart(request.getCartId());
            return new BaseStandardResponse<>(response);
        }
        log.info("Payment failed");
        return new BaseStandardResponse<>(response);
    }

}
