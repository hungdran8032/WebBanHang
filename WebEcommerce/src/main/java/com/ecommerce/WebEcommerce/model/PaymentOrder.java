package com.ecommerce.WebEcommerce.model;

import com.ecommerce.WebEcommerce.domain.PaymentMethod;
import com.ecommerce.WebEcommerce.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod method;

    private String paymentLinkId;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order> orders = new HashSet<>();

}
