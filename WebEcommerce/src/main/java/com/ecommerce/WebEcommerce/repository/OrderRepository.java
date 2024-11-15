package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long sellerId);
}
