package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Order;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySellerId(Seller seller);
    List<Transaction> getAllTransactions();
}
