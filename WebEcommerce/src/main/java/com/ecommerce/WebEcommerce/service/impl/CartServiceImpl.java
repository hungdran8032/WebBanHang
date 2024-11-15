package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.CartItem;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.repository.CartItemRepository;
import com.ecommerce.WebEcommerce.repository.CartRepository;
import com.ecommerce.WebEcommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem cartItemPresent  = cartItemRepository.findByCartAndProductAndSize(cart, product,size);

        if(cartItemPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);
            int totalPrice = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());
            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);
            return cartItemRepository.save(cartItem);
        }

        return cartItemPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem = 0;
        for(CartItem cartItem: cart.getCartItems()){
            totalPrice+= cartItem.getMrpPrice();
            totalDiscountPrice+= cartItem.getSellingPrice();
            totalItem+=cartItem.getQuantity();
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountPrice));
        cart.setTotalItem(totalItem);

        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            return 0;
        }
        double discount = (mrpPrice - sellingPrice);
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int) discountPercentage;
    }
}
