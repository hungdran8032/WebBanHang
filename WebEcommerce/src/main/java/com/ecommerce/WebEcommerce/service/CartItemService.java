package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.CartItem;

public interface CartItemService {
    public CartItem updateCartItem(Long userId, Long idProduct, CartItem cartItem) throws Exception;
    public void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItem findCartItemById(Long id) throws Exception;

}
