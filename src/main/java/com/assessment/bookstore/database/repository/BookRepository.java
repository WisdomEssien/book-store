package com.assessment.bookstore.database.repository;

import com.assessment.bookstore.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByIsbnIn(List<String> isbns);

    @Query("SELECT b FROM Book b WHERE " +
            "b.title LIKE CONCAT('%',:criterion, '%') " +
            "Or b.author LIKE CONCAT('%', :criterion, '%') "+
            "Or b.yearOfPublication = :criterion "+
            "Or b.genre LIKE CONCAT('%', :criterion, '%')")
    Optional<List<Book>> searchBook(String criterion);
}
