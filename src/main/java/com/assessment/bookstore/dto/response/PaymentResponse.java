package com.assessment.bookstore.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PaymentResponse {
    private String transactionId;
    private BigDecimal amount;
    private boolean paymentComleted;
}
