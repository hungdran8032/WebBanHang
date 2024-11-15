package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.CartItem;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.repository.CartItemRepository;
import com.ecommerce.WebEcommerce.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long idProduct, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(idProduct);

        User cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("Ban khong the them gio hang vui long dang nhap!");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem cartItem = findCartItemById(cartItemId);
        User cartItemUser = cartItem.getCart().getUser();
        if(cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(cartItem);
        }
        else throw new Exception("Ban khong the xoa item nay trong gio hang");
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(()-> new Exception("Khong tim thay san pham nay trong gio hang"));
    }
}
