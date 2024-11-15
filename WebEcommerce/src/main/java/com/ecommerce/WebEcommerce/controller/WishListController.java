package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.model.WishList;
import com.ecommerce.WebEcommerce.service.ProductService;
import com.ecommerce.WebEcommerce.service.UserService;
import com.ecommerce.WebEcommerce.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wish-list")
public class WishListController {
    private final WishListService wishListService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<WishList> getWishListByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        // Gọi service để lấy wishlist của người dùng
        WishList wishList = wishListService.getWishListByUserId(user);

        return ResponseEntity.ok(wishList);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishList(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.getProductById(productId);
        WishList updatedWishList  = wishListService.addProductToWishList(user, product);
        return ResponseEntity.ok(updatedWishList );


    }
}
