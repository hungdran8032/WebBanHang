package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Order;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.Transaction;
import com.ecommerce.WebEcommerce.repository.SellerRepository;
import com.ecommerce.WebEcommerce.repository.TransactionRepository;
import com.ecommerce.WebEcommerce.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller = sellerRepository.findById(order.getSellerId()).get();
        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(Seller seller) {

        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
