package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.Coupon;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.repository.CartRepository;
import com.ecommerce.WebEcommerce.repository.CouponRepository;
import com.ecommerce.WebEcommerce.repository.UserRepository;
import com.ecommerce.WebEcommerce.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);
        Cart cart = cartRepository.findByUserId(user.getId());
        if(coupon == null ){
            throw new Exception("Khong co ma giam gia nay");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw  new Exception("Ma giam gia nay da dung");
        }
        if(orderValue < coupon.getMinimumOrderValue()){
            throw  new Exception("Ma giam gia phai nho hon gia tien mua hang");
        }

        if(coupon.isActive()
                && LocalDate.now().isAfter(coupon.getValidityStartDate())
                && LocalDate.now().isBefore(coupon.getValidityEndDate())
        ){
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            double discountPrice = (cart.getTotalSellingPrice()* coupon.getDiscountPercentage())/100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountPrice);

            cart.setCouponCode(code);
            cartRepository.save(cart);
        }
        throw  new Exception("Ma giam gia nay khong kha dung");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);
        if(coupon == null ){
            throw new Exception("Khong co ma giam gia nay");
        }
        Cart cart = cartRepository.findByUserId(user.getId());
        double discountPrice = (cart.getTotalSellingPrice()* coupon.getDiscountPercentage())/100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCouponById(Long id) throws Exception {
        return couponRepository.findById(id).orElseThrow(()-> new Exception("Khong tim thay ma giam gia nay"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {
        findCouponById(id);
        couponRepository.deleteById(id);
    }
}
