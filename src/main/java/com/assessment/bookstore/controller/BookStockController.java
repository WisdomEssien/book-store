package com.assessment.bookstore.controller;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.dto.request.BookRequest;
import com.assessment.bookstore.dto.request.StockRequest;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.service.BookStoreService;
import com.assessment.bookstore.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.assessment.bookstore.util.AppConstants.STOCK;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RestController
@RequestMapping(STOCK)
@RequiredArgsConstructor
public class BookStockController {

    private final InventoryService inventoryService;

    @PostMapping("{isbn}/adjust")
    public BaseStandardResponse<StockRequest> adjustStock(@PathVariable String isbn,
                                                  @Valid @RequestBody StockRequest request){
        return inventoryService.adjustStock(isbn, request.getQuantity());
    }

    @GetMapping({"", "/{isbn}"})
    public BaseResponse listBook(@PathVariable(required = false) String isbn){
        return inventoryService.getStocks(isbn);
    }

}
