package com.ecommerce.WebEcommerce.model;

import lombok.*;

import java.util.List;

@Data
public class Home {
    private List<HomeCategory> grid;
    private List<HomeCategory> shopByCategories;
    private List<HomeCategory> electricByCategories;
    private List<HomeCategory> dealsCategories;

    private List<Deal> deals;
}
