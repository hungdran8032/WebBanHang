package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.model.VerificationCode;
import com.ecommerce.WebEcommerce.repository.UserRepository;
import com.ecommerce.WebEcommerce.request.LoginOtpRequest;
import com.ecommerce.WebEcommerce.request.LoginRequest;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.response.AuthResponse;
import com.ecommerce.WebEcommerce.response.SignUpRequest;
import com.ecommerce.WebEcommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest request) throws Exception {
        String jwt = authService.createUser(request);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Dang ky thanh cong");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody LoginOtpRequest request) throws Exception {
        authService.sendLoginOtp(request.getEmail(),request.getRole());

        ApiResponse res = new ApiResponse();
        res.setMessage("Gửi mã thành công");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> LoginHandler(@RequestBody LoginRequest request) throws Exception {
        AuthResponse authResponse = authService.signing(request);

        return ResponseEntity.ok(authResponse);
    }
}
