package com.assessment.bookstore.dto.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PaymentRequest {
    @NotNull
    @Positive(message = "CartId allows only positive numbers")
    private Long cartId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.00", message = "Amount must be greater than zero")
    @Digits(integer = 1000000000, fraction = 2)
    private BigDecimal amount;

    @Pattern(regexp = "(?i)^(WEB|USSD|Transfer)$",
            message = "PaymentType possible values: WEB, USSD or Transfer")
    @NotBlank(message = "PaymentType is required")
    private String paymentType;
}
