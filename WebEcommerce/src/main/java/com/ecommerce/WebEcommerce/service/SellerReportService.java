package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);

}
