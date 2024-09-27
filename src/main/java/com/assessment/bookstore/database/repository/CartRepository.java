package com.assessment.bookstore.database.repository;

import com.assessment.bookstore.database.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
