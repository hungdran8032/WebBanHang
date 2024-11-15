package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.CartItem;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;

public interface CartService {
    public CartItem addCartItem(
        User user,
        Product product,
        String size,
        int quantity
    );
    public Cart findUserCart(User user);
}
