package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.response.AuthResponse;
import com.ecommerce.WebEcommerce.response.SignUpRequest;
import com.ecommerce.WebEcommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user/profile")
    public ResponseEntity<User> profileUserHandler(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return ResponseEntity.ok(user);
    }
}
