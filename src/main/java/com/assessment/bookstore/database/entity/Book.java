package com.assessment.bookstore.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "book")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "isbn")
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

    @ManyToMany(mappedBy = "isbns", fetch = FetchType.LAZY)
    @JsonBackReference()
    private Set<Cart> carts;
}
