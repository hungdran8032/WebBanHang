package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.config.JwtProvider;
import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.model.Cart;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.model.VerificationCode;
import com.ecommerce.WebEcommerce.repository.CartRepository;
import com.ecommerce.WebEcommerce.repository.SellerRepository;
import com.ecommerce.WebEcommerce.repository.UserRepository;
import com.ecommerce.WebEcommerce.repository.VerificationCodeRepository;
import com.ecommerce.WebEcommerce.request.LoginRequest;
import com.ecommerce.WebEcommerce.response.AuthResponse;
import com.ecommerce.WebEcommerce.response.SignUpRequest;
import com.ecommerce.WebEcommerce.service.AuthService;
import com.ecommerce.WebEcommerce.service.EmailService;
import com.ecommerce.WebEcommerce.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomerUserServiceImpl customerUserService;
    @Override
    public void sendLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";
//        String SELLER_PREFIX = "seller_";

        if(email.startsWith(SIGNING_PREFIX)){
            email = email.substring(SIGNING_PREFIX.length());
            if(role.equals(USER_ROLE.ROLE_SELLER)){
                Seller seller = sellerRepository.findByEmail(email);
                if(seller == null){
                    throw new Exception("Khong tim thay nguoi ban nay!");
                }

            }
            else{
                User user = userRepository.findByEmail(email);
                if(user == null){
                    throw new Exception("Khong tim thay nguoi dung nay!");
                }
            }

        }

        VerificationCode isExist = verificationCodeRepository.findByEmail(email);
        if (isExist != null){
            verificationCodeRepository.delete(isExist);
        }
        String otp = OtpUtils.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(email);
        verificationCodeRepository.save(verificationCode);

        String subject = "OTP LOGIN";
        String text ="Mã login của bạn là: " + otp;

        emailService.sendVerificationOtpEmail(email, otp,subject,text);
    }

    @Override
    public String createUser(SignUpRequest req) throws Exception {
//        String SIGNING_PREFIX = "signing_";
//        if(req.getEmail()){
//
//        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());
        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())){
            throw new Exception("Sai OTP");
        }

        User user= userRepository.findByEmail(req.getEmail());
        if(user==null){
            User createdUser = new User();
            createdUser.setEmail(req.getEmail());
            createdUser.setFullName(req.getFullName());
            createdUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createdUser.setMobile("84967061645");
            createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
            User savedUser = userRepository.save(createdUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);



        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signing(LoginRequest req) {
        String userName = req.getEmail();
        String otp = req.getOtp();
        Authentication authentication = authenticate(userName, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Đăng nhập thành công");

        Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
        String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String userName, String otp) {
        UserDetails userDetails = customerUserService.loadUserByUsername(userName);
        String SELLER_PREFIX = "seller_";
        if(userName.startsWith(SELLER_PREFIX)){
            userName = userName.substring(SELLER_PREFIX.length());
        }

        if (userDetails==null){
            throw new BadCredentialsException("Mật khẩu hoặc tài khoản không đúng");
        }
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(userName);

        if (verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new BadCredentialsException("Sai otp");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
}
