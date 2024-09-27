package com.assessment.bookstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CartRequest {

    @NotNull
    @Positive(message = "CartId allows only positive numbers")
    private Long cartId;

    @Pattern(regexp = "^[0-9-]+$", message = "ISBN must contain only numbers and dash(-)")
    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull
    @Positive(message = "Quantity allows only positive numbers")
    private Integer quantity;

}
