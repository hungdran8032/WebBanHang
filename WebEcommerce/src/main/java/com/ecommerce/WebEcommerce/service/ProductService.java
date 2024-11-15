package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.exceptions.ProductException;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {
    public Product createProduct(CreateProductRequest request, Seller seller);
    public Product updateProduct(Long idProduct, Product product) throws ProductException;
    public void deleteProduct(Long idProduct) throws ProductException;
    public Product getProductById(Long idProduct) throws ProductException;
    public List<Product> searchProduct(String query);
    public Page<Product> getAllProduct(
            String category, String brand, String color, String size,
            Integer minPrice, Integer maxPrice, Integer minDiscount,
            String sort, String stock, Integer pageNumber
    );
    List<Product> getProductBySellerId (Long idSeller);
}
