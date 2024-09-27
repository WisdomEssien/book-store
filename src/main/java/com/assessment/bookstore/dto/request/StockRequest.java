package com.assessment.bookstore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    @NotNull
    private Integer quantity;
}
