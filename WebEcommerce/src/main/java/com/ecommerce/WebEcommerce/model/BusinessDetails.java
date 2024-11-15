package com.ecommerce.WebEcommerce.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
public class BusinessDetails {
    private String businessName, businessEmail, businessMobile, businessAddress, logo, banner;
}
