package com.assessment.bookstore.service;

import com.assessment.bookstore.database.entity.Book;
import com.assessment.bookstore.database.repository.BookRepository;
import com.assessment.bookstore.dto.request.BookRequest;
import com.assessment.bookstore.dto.response.BaseCollectionResponse;
import com.assessment.bookstore.dto.response.BaseStandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.assessment.bookstore.util.AppConstants.EM_SAVING_TO_DATABASE;
import static com.assessment.bookstore.util.ResponseCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookRepository bookRepository;

    public BaseStandardResponse<Book> createBook(BookRequest request) {
        log.info("Creating new book");
        Book book = new Book();
        BeanUtils.copyProperties(request, book);

        BaseStandardResponse<Book> reponse;
        try {
            Book createdBook = bookRepository.save(book);
            log.info("saved new book to the database");
            reponse = new BaseStandardResponse<>(createdBook);
        } catch (Exception ex) {
            log.error(EM_SAVING_TO_DATABASE, ex);
            reponse = new BaseStandardResponse<>(DATABASE_SAVE_ERROR);
        }
        log.info("Final response :: {}", reponse);
        return reponse;
    }

    public BaseStandardResponse<Book> updateBook(BookRequest request) {
        log.info("Updating existing book with ISBN {}", request.getIsbn());
        Optional<Book> optionalBook = bookRepository.findByIsbn(request.getIsbn());
        if (optionalBook.isEmpty()) {
            return new BaseStandardResponse<>(UNABLE_TO_LOCATE_RECORD);
        }

        Book book = optionalBook.get();
        BeanUtils.copyProperties(request, book);

        BaseStandardResponse<Book> reponse;
        try {
            Book createdBook = bookRepository.save(book);
            log.info("Updated new book to the database");
            reponse = new BaseStandardResponse<>(createdBook);
        } catch (Exception ex) {
            log.error(EM_SAVING_TO_DATABASE, ex);
            reponse = new BaseStandardResponse<>(DATABASE_SAVE_ERROR);
        }
        log.info("Final response :: {}", reponse);
        return reponse;
    }

    public BaseCollectionResponse<Book> getBooks() {
        log.info("Get all books");
        return new BaseCollectionResponse<>(bookRepository.findAll());
    }

    public BaseStandardResponse<Book> getBook(String isbn) {
        log.info("Get book with ISBN {}", isbn);
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        return optionalBook
                .map(BaseStandardResponse::new)
                .orElseGet(() -> new BaseStandardResponse<>(UNABLE_TO_LOCATE_RECORD));
    }

    public BaseStandardResponse<Book> deleteBook(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isEmpty()) {
            return new BaseStandardResponse<>(UNABLE_TO_LOCATE_RECORD);
        }
        bookRepository.deleteById(optionalBook.get().getIsbn());
        log.info("deleted book with ISBN {}", isbn);
        return new BaseStandardResponse<>(SUCCESS);
    }

    public BaseCollectionResponse<Book> searchBook(String criterion) {
        log.info("Searching for books with criterion {}", criterion);
        Optional<List<Book>> optionalBook = bookRepository.searchBook(criterion);
//        if(optionalBook.isEmpty()) {
//            return new BaseCollectionResponse<>(UNABLE_TO_LOCATE_RECORD);
//        }
//        return new BaseCollectionResponse<>(optionalBook.get());
        return optionalBook.map(BaseCollectionResponse::new).orElseGet(() -> new BaseCollectionResponse<>(UNABLE_TO_LOCATE_RECORD));
    }

}
