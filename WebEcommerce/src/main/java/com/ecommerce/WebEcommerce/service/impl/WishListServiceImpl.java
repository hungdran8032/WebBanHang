package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.model.WishList;
import com.ecommerce.WebEcommerce.repository.ProductRepository;
import com.ecommerce.WebEcommerce.repository.UserRepository;
import com.ecommerce.WebEcommerce.repository.WishListRepository;
import com.ecommerce.WebEcommerce.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;

    @Override
    public WishList createWishList(User user) {

        WishList wishList = new WishList();
        wishList.setUser(user);
        return wishListRepository.save(wishList);
    }

    @Override
    public WishList getWishListByUserId(User user) {
        WishList wishList = wishListRepository.findByUserId(user.getId());
        if(wishList==null){
            wishList = createWishList(user);
        }
        return wishList;
    }

    @Override
    public WishList addProductToWishList(User user, Product product) {
        WishList wishList = getWishListByUserId(user);
        if (wishList.getProducts().contains(product)) {
            wishList.getProducts().remove(product);
        }
        else wishList.getProducts().add(product);
        return wishListRepository.save(wishList);
    }
}
