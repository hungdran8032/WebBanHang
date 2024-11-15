package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.config.JwtProvider;
import com.ecommerce.WebEcommerce.domain.AccountStatus;
import com.ecommerce.WebEcommerce.domain.USER_ROLE;
import com.ecommerce.WebEcommerce.exceptions.SellerException;
import com.ecommerce.WebEcommerce.model.Address;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.repository.AddressRepository;
import com.ecommerce.WebEcommerce.repository.SellerRepository;
import com.ecommerce.WebEcommerce.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder newPasswordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws SellerException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller getSellerByEmail(String email) throws SellerException {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null){
            throw new SellerException("Không tìm thấy người bán này");
        }
        return seller;
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());

        if (sellerExist != null){
            throw new Exception("Người bán này đã tồn tại");

        }

        Address saveAddress = addressRepository.save(seller.getPickupAddress());
        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(newPasswordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(saveAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerException("Không tìm thấy người bán này"));
    }



    @Override
    public List<Seller> getAllSeller(AccountStatus accountStatus) {
        return sellerRepository.findByAccountStatus(accountStatus);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller sellerExist = this.getSellerById(id);

        if(seller.getSellerName() != null){
            sellerExist.setSellerName(seller.getSellerName());
        }
        if (seller.getMobile() != null){
            sellerExist.setMobile(seller.getMobile());
        }
        if (seller.getEmail() != null){
            sellerExist.setEmail(seller.getEmail());
        }
        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolderName() != null
                && seller.getBankDetails().getIfscCode() != null
                && seller.getBankDetails().getAccountNumber() != null
        ){
            sellerExist.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
            sellerExist.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
            sellerExist.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
        }
        if (seller.getBusinessDetails() != null
                && seller.getBusinessDetails().getBusinessName() != null){
            sellerExist.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
        }
        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getMobile() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
        ){
            sellerExist.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
            sellerExist.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
            sellerExist.getPickupAddress().setCity(seller.getPickupAddress().getCity());
            sellerExist.getPickupAddress().setState(seller.getPickupAddress().getState());
        }
        if (seller.getGSTIN() != null){
            sellerExist.setGSTIN(seller.getGSTIN());
        }


        return sellerRepository.save(sellerExist);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus accountStatus) throws Exception {

        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(accountStatus);
        return sellerRepository.save(seller);
    }
}
