package com.assessment.bookstore.controller;

import com.assessment.bookstore.database.entity.Cart;
import com.assessment.bookstore.dto.request.CartRequest;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.exception.BookStoreException;
import com.assessment.bookstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.assessment.bookstore.util.AppConstants.CART;

@Slf4j
@RestController
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/reserve")
    public BaseStandardResponse<Cart> addToCart(@Valid @RequestBody CartRequest request) throws BookStoreException {
        return cartService.addToCart(request);
    }

    @GetMapping("/reserve/{cartId}")
    public BaseStandardResponse<Cart> getCart(@PathVariable Long cartId){
        return cartService.getCart(cartId);
    }

    @DeleteMapping("/reserve/{cartId}")
    public BaseStandardResponse<Cart> deleteCart(@PathVariable Long cartId){
        return cartService.deleteCart(cartId);
    }

}
