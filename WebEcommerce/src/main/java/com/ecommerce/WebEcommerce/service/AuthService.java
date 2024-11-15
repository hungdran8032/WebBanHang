package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.request.LoginRequest;
import com.ecommerce.WebEcommerce.response.AuthResponse;
import com.ecommerce.WebEcommerce.response.SignUpRequest;

public interface AuthService {
    void sendLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignUpRequest req) throws Exception;
    AuthResponse signing(LoginRequest req);

}
