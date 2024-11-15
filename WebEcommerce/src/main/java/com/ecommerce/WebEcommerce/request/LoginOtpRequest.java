package com.ecommerce.WebEcommerce.request;

import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;
}
