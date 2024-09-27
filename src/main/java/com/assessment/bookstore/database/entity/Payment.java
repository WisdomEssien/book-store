package com.assessment.bookstore.database.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "payment")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne()
    @JoinColumn(name = "cartId")
    private Cart cartId;

    @Column(name = "paymentType", length = 10)
    private String paymentType;

    @Column(name = "paymentCompleted", length = 5)
    private boolean paymentCompleted;

    private Long userId;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
