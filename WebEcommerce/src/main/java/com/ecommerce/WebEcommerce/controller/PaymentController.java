package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.*;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.response.PaymentLinkResponse;
import com.ecommerce.WebEcommerce.service.*;
import com.razorpay.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        PaymentLinkResponse paymentLinkResponse;
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentId);

        boolean paymentSuccess = paymentService.proceedPaymentOrder(
                paymentOrder, paymentId, paymentLinkId
        );
        if (paymentSuccess){
            for (Order order : paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport report = sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders());
                report.setTotalEarnings(report.getTotalEarnings() + order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales() + order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }
        }

        ApiResponse res = new ApiResponse();
        res.setMessage("Payment success");
//        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
