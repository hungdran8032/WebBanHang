package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
