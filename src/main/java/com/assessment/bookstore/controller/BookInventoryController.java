package com.assessment.bookstore.controller;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.dto.request.BookRequest;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import com.assessment.bookstore.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.assessment.bookstore.util.AppConstants.INVENTORY;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RestController
@RequestMapping(INVENTORY)
@RequiredArgsConstructor
public class BookInventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public BaseStandardResponse<Book> addBook(@Valid @RequestBody BookRequest request){
        return inventoryService.createBook(request);
    }

    @PutMapping
    public BaseStandardResponse<Book> editBook(@Valid @RequestBody BookRequest request){
        return inventoryService.updateBook(request);
    }

    @GetMapping({"", "/{isbn}"})
    public BaseResponse listBook(@PathVariable(required = false) String isbn){
        return hasText(isbn) ? inventoryService.getBook(isbn) : inventoryService.getBooks();
    }

    @DeleteMapping("/{isbn}")
    public BaseStandardResponse<Book> removeBook(@PathVariable String isbn){
        return inventoryService.deleteBook(isbn);
    }

    @GetMapping("/search")
    public BaseCollectionResponse<Book> searchBook(@RequestParam String criterion){
        return inventoryService.searchBook(criterion);
    }

}
