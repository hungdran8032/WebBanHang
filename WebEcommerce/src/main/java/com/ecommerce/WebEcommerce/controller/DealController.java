package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Deal;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deal")
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal){
        Deal createDeal = dealService.createDeal(deal);
        return new ResponseEntity<>(createDeal, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable Long id, @RequestBody Deal deal) throws Exception {
        Deal updateDeal = dealService.updateDeal(deal, id);
        return ResponseEntity.ok(updateDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeal(@PathVariable Long id) throws Exception {
        dealService.deleteDeal(id);
        ApiResponse res = new ApiResponse();
        res.setMessage("Xoa thanh cong");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
