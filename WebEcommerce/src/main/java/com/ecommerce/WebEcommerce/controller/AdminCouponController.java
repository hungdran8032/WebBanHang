package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.Coupon;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.service.CartService;
import com.ecommerce.WebEcommerce.service.CouponService;
import com.ecommerce.WebEcommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class AdminCouponController {
    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart;
        if(apply.equals("true")){
            cart = couponService.applyCoupon(code,orderValue,user);
        }
        else{
            cart = couponService.removeCoupon(code,user);
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
        Coupon createCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> createCoupon(@PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("Xoa thanh cong");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupon (){
        List<Coupon> coupons = couponService.findAllCoupon();
        return ResponseEntity.ok(coupons);
    }
}
