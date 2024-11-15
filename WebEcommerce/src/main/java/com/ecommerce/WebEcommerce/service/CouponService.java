package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.Coupon;
import com.ecommerce.WebEcommerce.model.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon (String code, double orderValue, User user) throws Exception;
    Cart removeCoupon (String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void deleteCoupon(Long id) throws Exception;
}
