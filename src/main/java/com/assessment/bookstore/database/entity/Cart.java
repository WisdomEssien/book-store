package com.assessment.bookstore.database.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="Cart_Book",
            joinColumns = {@JoinColumn(name="cart_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="isbn", referencedColumnName="isbn")}
    )
    private Set<Book> isbns;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reserved_until")
    private LocalDateTime reservedUntil = LocalDateTime.now().plusDays(3);

}
