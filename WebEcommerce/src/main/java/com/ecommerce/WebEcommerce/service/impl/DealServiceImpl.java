package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.Deal;
import com.ecommerce.WebEcommerce.model.HomeCategory;
import com.ecommerce.WebEcommerce.repository.DealRepository;
import com.ecommerce.WebEcommerce.repository.HomeCategoryRepository;
import com.ecommerce.WebEcommerce.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;
    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory homeCategory = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(homeCategory);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepository.findById(deal.getId()).orElse(null);
        HomeCategory homeCategory = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
        if (existingDeal != null){
            if (deal.getDiscount() != null){
                existingDeal.setDiscount(deal.getDiscount());
            }
            if (homeCategory != null){
                existingDeal.setCategory(deal.getCategory());
            }
            return dealRepository.save(existingDeal);
        }
        throw  new Exception("Deal nay khong co");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal1 = dealRepository.findById(id).orElseThrow(()->new Exception("Khong tim thay"));
        dealRepository.delete(deal1);
    }
}
