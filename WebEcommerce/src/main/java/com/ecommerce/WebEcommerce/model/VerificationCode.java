package com.ecommerce.WebEcommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String otp;

    @OneToOne
    private User user;

    @OneToOne
    private Seller seller;
}
