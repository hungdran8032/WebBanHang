package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.CartItem;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.request.AddItemRequest;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.service.CartItemService;
import com.ecommerce.WebEcommerce.service.CartService;
import com.ecommerce.WebEcommerce.service.ProductService;
import com.ecommerce.WebEcommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestHeader("Authorization") String jwt,
            @RequestBody AddItemRequest request
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.getProductById(request.getIdProduct());

        CartItem item = cartService.addCartItem(user, product, request.getSize(), request.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Them thanh cong");
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{idCartItem}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long idCartItem
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), idCartItem);

        ApiResponse res = new ApiResponse();
        res.setMessage("Xoa thanh cong");
        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{idCartItem}")
    public ResponseEntity<CartItem> updateCartItemHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long idCartItem,
            @RequestBody CartItem cartItem
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        CartItem updateCartItem = null;
        if(cartItem.getQuantity()>0){
            updateCartItem=cartItemService.updateCartItem(user.getId(), idCartItem, cartItem);
        }
        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }
}
