package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.CartItem;
import com.ecommerce.WebEcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
