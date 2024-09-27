package com.assessment.bookstore.service;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.database.entity.Cart;
import com.assessment.bookstore.database.entity.Payment;
import com.assessment.bookstore.database.repository.CartRepository;
import com.assessment.bookstore.database.repository.PaymentRepository;
import com.assessment.bookstore.dto.request.CartRequest;
import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.request.StockRequest;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.dto.response.PaymentResponse;
import com.assessment.bookstore.exception.BookStoreException;
import com.assessment.bookstore.feign.PaymentClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.assessment.bookstore.util.ResponseCode.*;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CartService cartService;
    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;

    public BaseStandardResponse<Payment> payment(PaymentRequest request) {
        Optional<Cart> cartOptional = cartRepository.findById(request.getCartId());
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            PaymentResponse response = paymentClient.pay(request);
            if (response.isPaymentComleted()) {
                log.info("Payment successful");
                Payment payment = new Payment();
                payment.setCartId(cart);
                payment.setPaymentType(request.getPaymentType());
                payment.setPaymentCompleted(response.isPaymentComleted());
                payment.setUserId(request.getUserId());
                paymentRepository.save(payment);
                //cartService.deleteCart(request.getCartId());
                return new BaseStandardResponse<>(payment);
            }
        }
        log.info("Payment failed");
        return new BaseStandardResponse<>(new Payment());
    }

    public BaseCollectionResponse<Payment> history(Long userId) {
        Optional<List<Payment>> paymentOptional = paymentRepository.findByUserId(userId);
        return paymentOptional.map(BaseCollectionResponse::new).orElseGet(() -> new BaseCollectionResponse<>(emptyList()));
    }

}
