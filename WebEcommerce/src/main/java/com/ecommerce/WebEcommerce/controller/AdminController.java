package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.domain.AccountStatus;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final SellerService sellerService;

    @PatchMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updateSellerStatus(
            @PathVariable Long id,
            @PathVariable AccountStatus accountStatus
    ) throws Exception {
        Seller updateSeller = sellerService.updateSellerAccountStatus(id, accountStatus);
        return ResponseEntity.ok(updateSeller);
    }
}
