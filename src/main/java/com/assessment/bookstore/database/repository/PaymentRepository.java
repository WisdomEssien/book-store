package com.assessment.bookstore.database.repository;

import com.assessment.bookstore.database.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<List<Payment>> findByUserId(long userId);
}
