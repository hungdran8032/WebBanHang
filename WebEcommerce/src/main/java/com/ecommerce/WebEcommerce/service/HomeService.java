package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Home;
import com.ecommerce.WebEcommerce.model.HomeCategory;

import java.util.List;

public interface HomeService {
    Home createHomePageData(List<HomeCategory> categories);
}
