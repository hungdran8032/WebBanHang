package com.ecommerce.WebEcommerce.response;

import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
