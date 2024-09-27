package com.assessment.bookstore.service;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.database.repository.BookRepository;
import com.assessment.bookstore.dto.request.StockRequest;
import com.assessment.bookstore.dto.request.StockResponse;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.assessment.bookstore.util.ResponseCode.*;
import static java.util.Collections.emptyList;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final BookStoreService bookStoreService;
    private final BookRepository bookRepository;

    public BaseStandardResponse<StockRequest> adjustStock(String isbn, Integer quantity) {
        BaseStandardResponse<Book> bookResp = bookStoreService.getBook(isbn);
        if (SUCCESS.getCode().equals(bookResp.getResponseCode())) {
            Book book = bookResp.getData();
            int newQuantity = book.getQuantity() + quantity;
            if(newQuantity < 0) {
                newQuantity = 0;
            }
            book.setQuantity(newQuantity);
            bookRepository.save(bookResp.getData());
            return new BaseStandardResponse<>(new StockRequest(newQuantity));
        }
        return new BaseStandardResponse<>(getResponseByCode(bookResp.getResponseCode()));
    }

    public BaseResponse getStocks(String isbn) {
        if (hasText(isbn)) {
            BaseStandardResponse<Book> bookResp = bookStoreService.getBook(isbn);
            if (SUCCESS.getCode().equals(bookResp.getResponseCode())) {
                StockResponse stockResponse = new StockResponse();
                BeanUtils.copyProperties(bookResp.getData(), stockResponse);
                return new BaseStandardResponse<>(stockResponse);
            }
            return new BaseStandardResponse<>(getResponseByCode(bookResp.getResponseCode()));
        }

        BaseCollectionResponse<Book> bookResps = bookStoreService.getBooks();
        if (bookResps.getData().isEmpty()) {
            return new BaseCollectionResponse<>(emptyList());
        }
        return new BaseCollectionResponse<>(bookResps.getData()
                .stream()
                .map(book -> new StockResponse(book.getIsbn(), book.getTitle(), book.getQuantity()))
                .toList());
    }
}
