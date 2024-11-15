package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByUserId(Long userId);
}
