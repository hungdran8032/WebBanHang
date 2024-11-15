package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.exceptions.SellerException;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.Transaction;
import com.ecommerce.WebEcommerce.service.SellerService;
import com.ecommerce.WebEcommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/seller")
    public ResponseEntity<List<Transaction>> getTransactionBySeller(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);

        List<Transaction> transactions = transactionService.getTransactionsBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }


    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
