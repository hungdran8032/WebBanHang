package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.model.WishList;

public interface WishListService {
    WishList createWishList(User user);
    WishList getWishListByUserId(User user);
    WishList addProductToWishList(User user, Product product);
}
