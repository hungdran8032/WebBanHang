package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.model.SellerReport;
import com.ecommerce.WebEcommerce.repository.SellerReportRepository;
import com.ecommerce.WebEcommerce.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private final SellerReportRepository sellerReportRepository;
    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());
        if(sr == null){
            SellerReport sellerReport = new SellerReport();
            sellerReport.setSeller(seller);
            return sellerReportRepository.save(sellerReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
