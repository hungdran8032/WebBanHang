package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.domain.AccountStatus;
import com.ecommerce.WebEcommerce.exceptions.SellerException;
import com.ecommerce.WebEcommerce.model.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws SellerException;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail(String email) throws SellerException;
    List<Seller> getAllSeller(AccountStatus accountStatus);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email, String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerId, AccountStatus accountStatus) throws Exception;

}
