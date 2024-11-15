package com.ecommerce.WebEcommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BankDetails {
    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;
}
