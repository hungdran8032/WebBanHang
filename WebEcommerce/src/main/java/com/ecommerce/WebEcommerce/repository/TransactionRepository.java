package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySellerId(Long sellerId);
}
