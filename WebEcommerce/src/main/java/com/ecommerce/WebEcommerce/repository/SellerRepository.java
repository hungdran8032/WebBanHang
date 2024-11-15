package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.domain.AccountStatus;
import com.ecommerce.WebEcommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
    List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
