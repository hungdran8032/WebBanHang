package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long id);
}
