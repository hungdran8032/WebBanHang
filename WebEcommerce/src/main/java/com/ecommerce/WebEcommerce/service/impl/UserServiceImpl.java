package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.config.JwtProvider;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.repository.UserRepository;
import com.ecommerce.WebEcommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String user = jwtProvider.getEmailFromJwtToken(jwt);

        return this.findUserByEmail(user);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("Không tìm thấy email này: "+ email);
        }
        return user;
    }
}
