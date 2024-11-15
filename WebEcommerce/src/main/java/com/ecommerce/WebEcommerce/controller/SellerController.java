package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.config.JwtProvider;
import com.ecommerce.WebEcommerce.domain.AccountStatus;
import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.exceptions.SellerException;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.SellerReport;
import com.ecommerce.WebEcommerce.model.VerificationCode;
import com.ecommerce.WebEcommerce.repository.VerificationCodeRepository;
import com.ecommerce.WebEcommerce.request.LoginRequest;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.response.AuthResponse;
import com.ecommerce.WebEcommerce.response.SignUpRequest;
import com.ecommerce.WebEcommerce.service.AuthService;
import com.ecommerce.WebEcommerce.service.EmailService;
import com.ecommerce.WebEcommerce.service.SellerReportService;
import com.ecommerce.WebEcommerce.service.SellerService;
import com.ecommerce.WebEcommerce.utils.OtpUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final VerificationCodeRepository verificationCodeRepository;
    private final AuthService authService;
    private final SellerService sellerService;
    private final EmailService emailService;
//    private final JwtProvider jwtProvider;
    private final SellerReportService sellerReportService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSellerHandler(@RequestBody LoginRequest req) throws Exception {
        String otp = req.getOtp();
        String email = req.getEmail();

        req.setEmail("seller_" + email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);

    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerHandler(@PathVariable String otp) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new Exception("Sai ma otp");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);
        return new ResponseEntity<>(seller, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Seller> createSellerHandler(@RequestBody Seller seller) throws Exception, MessagingException {
        Seller saveSeller = sellerService.createSeller(seller);

        String otp = OtpUtils.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "XÁCCCCCCCCCCCCC THỰCCCCCCCCCCCCCCC";
        String text ="Link xác thực của bạn nè: ";
        String frontend_url = "http://localhost:3000/verify-seller/";

        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text + frontend_url);
        return new ResponseEntity<>(saveSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception{
        Seller seller = sellerService.getSellerProfile(jwt);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/report")
    public  ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws  Exception{

//        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerProfile(jwt);
        SellerReport report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false)AccountStatus accountStatus){
        List<Seller> sellers = sellerService.getAllSeller(accountStatus);
        return ResponseEntity.ok(sellers);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller (
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller) throws Exception{
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception{
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
