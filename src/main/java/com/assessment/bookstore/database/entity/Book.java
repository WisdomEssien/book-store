package com.assessment.bookstore.database.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "book")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    @Id
    private String isbn;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "genre", length = 20)
    private String genre;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "year_of_Publication", length = 4)
    private String yearOfPublication;

    @Column(name = "quantity")
    private Integer quantity;
}
