package com.ecommerce.WebEcommerce.repository;


import com.ecommerce.WebEcommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
