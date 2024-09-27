package com.assessment.bookstore.service;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.database.entity.Cart;
import com.assessment.bookstore.database.repository.CartRepository;
import com.assessment.bookstore.dto.request.CartRequest;
import com.assessment.bookstore.dto.request.StockRequest;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.exception.BookStoreException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.assessment.bookstore.util.ResponseCode.*;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final BookStoreService bookStoreService;
    private final InventoryService inventoryService;
    private final CartRepository cartRepository;

    @Transactional
    public BaseStandardResponse<Cart> addToCart(CartRequest request) throws BookStoreException {
        BaseStandardResponse<Book> bookResp = bookStoreService.getBook(request.getIsbn());
        if (SUCCESS.getCode().equals(bookResp.getResponseCode())) {
            if (bookResp.getData().getQuantity() < request.getQuantity()) {
                log.info("Quantity available is less than what is requested");
                throw new BookStoreException(OUT_OF_STOCK);
            }
            Cart cart = cartRepository.findById(request.getCartId()).orElse(new Cart());

            Set<Book> books = isNull(cart.getIsbns()) || cart.getIsbns().isEmpty()
                    ? new HashSet<>()
                    : cart.getIsbns();
            books.add(bookResp.getData());

            BaseStandardResponse<StockRequest> stockResp = inventoryService.adjustStock(request.getIsbn(), (request.getQuantity() * -1));
            if(SUCCESS.getCode().equals(stockResp.getResponseCode())) {
                cart.setId(request.getCartId());
                cart.setIsbns(books);
                cart.setQuantity(request.getQuantity());
                cartRepository.save(cart);
                log.info("saved cart to the database");
                return new BaseStandardResponse<>(cart);
            }
            log.info("Could not set new quantity for book with ISBN [{}]", request.getIsbn());
            return new BaseStandardResponse<>(getResponseByCode(stockResp.getResponseCode()));
        }
        log.info("Book with ISBN [{}] not found", request.getIsbn());
        return new BaseStandardResponse<>(getResponseByCode(bookResp.getResponseCode()));
    }

    public BaseStandardResponse<Cart> getCart(Long cartId) {
        log.info("Get cart items for cartId [{}]", cartId);
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        return optionalCart
                .filter(cart -> {
                    if(ChronoUnit.DAYS.between(cart.getReservedUntil(), LocalDateTime.now()) > 3) {
                        log.info("Exceeded reservation limit fo 3 days");
                        deleteCart(cartId);
                        return false;
                    }
                    return true;
                })
                .map(BaseStandardResponse::new)
                .orElseGet(() -> new BaseStandardResponse<>(UNABLE_TO_LOCATE_RECORD));
    }

    public BaseStandardResponse<Cart> deleteCart(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()) {
            return new BaseStandardResponse<>(UNABLE_TO_LOCATE_RECORD);
        }
        cartRepository.deleteById(optionalCart.get().getId());
        log.info("deleted Cart with Id {}", cartId);
        return new BaseStandardResponse<>(SUCCESS);
    }

}
