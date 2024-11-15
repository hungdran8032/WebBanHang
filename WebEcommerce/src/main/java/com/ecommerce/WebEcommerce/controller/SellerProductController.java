package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.exceptions.ProductException;
import com.ecommerce.WebEcommerce.exceptions.SellerException;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.request.CreateProductRequest;
import com.ecommerce.WebEcommerce.service.ProductService;
import com.ecommerce.WebEcommerce.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/product")
public class SellerProductController {
    private final SellerService sellerService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductBySellerId(@RequestHeader("Authorization") String jwt)
            throws Exception {
        Seller sellers = sellerService.getSellerProfile(jwt);
        List<Product> products = productService.getProductBySellerId(sellers.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwt
            ) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);
        Product product = productService.createProduct(request, seller);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductException {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductException {
            Product updateProduct = productService.updateProduct(id,product);
            return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }
}
