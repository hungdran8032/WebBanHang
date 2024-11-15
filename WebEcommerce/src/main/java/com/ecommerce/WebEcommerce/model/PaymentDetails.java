package com.ecommerce.WebEcommerce.model;

import com.ecommerce.WebEcommerce.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {
    private String paymentId, razorpayPaymentLinkId, razorpayPaymentLinkReferenceId, razorpayPaymentLinkStatus, razorpayPaymentId;
    private PaymentStatus status;

}
