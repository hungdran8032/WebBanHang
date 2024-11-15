package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {
    //Code lỏ của chatgpt, call api được những không ra nên phải dùng query
    @Query("SELECT p FROM Product p WHERE p.seller.id = :idSeller")
    List<Product> findSellerById(@Param("idSeller") Long idSeller);
    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE (:query IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "OR (:query IS NULL OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Product> searchProduct(@Param("query") String query);
}
