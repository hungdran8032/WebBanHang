package com.ecommerce.WebEcommerce.repository;

import com.ecommerce.WebEcommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
