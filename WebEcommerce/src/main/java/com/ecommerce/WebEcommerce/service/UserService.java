package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
