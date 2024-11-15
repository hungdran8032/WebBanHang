package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryId(String categoryId);
}
