package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.domain.HomeCategorySection;
import com.ecommerce.WebEcommerce.model.Deal;
import com.ecommerce.WebEcommerce.model.Home;
import com.ecommerce.WebEcommerce.model.HomeCategory;
import com.ecommerce.WebEcommerce.repository.DealRepository;
import com.ecommerce.WebEcommerce.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HomeServiceImpl implements HomeService {
    private final DealRepository dealRepository;
    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {
        List<HomeCategory> gridCategories = allCategories.stream()
                .filter(category -> category.getHomeCategorySection() == HomeCategorySection.GRID)
                .collect(Collectors.toList());
        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(category -> category.getHomeCategorySection() == HomeCategorySection.SHOP_BY_CATEGORIES)
                .collect(Collectors.toList());
        List<HomeCategory> electricCategories = allCategories.stream()
                .filter(category -> category.getHomeCategorySection() == HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());
        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(category -> category.getHomeCategorySection() == HomeCategorySection.DEALS)
                .toList();
        List<Deal> createdDeals = new ArrayList<>();
        if(dealRepository.findAll().isEmpty()) {
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getHomeCategorySection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(null, 10, category))
                    .collect(Collectors.toList());
            createdDeals = dealRepository.saveAll(deals);
        }else createdDeals = dealRepository.findAll();

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricByCategories(electricCategories);
        home.setDealsCategories(dealCategories);

        return home;
    }
}
