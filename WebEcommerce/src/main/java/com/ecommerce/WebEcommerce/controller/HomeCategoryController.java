package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Home;
import com.ecommerce.WebEcommerce.model.HomeCategory;
import com.ecommerce.WebEcommerce.service.HomeCategoryService;
import com.ecommerce.WebEcommerce.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {
    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategories(@RequestBody List<HomeCategory> homeCategories){
        List<HomeCategory> categories = homeCategoryService.createHomeCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategories(){
        List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategories(@PathVariable Long id, @RequestBody HomeCategory homeCategory) throws Exception {
        HomeCategory updateHC = homeCategoryService.updateHomeCategory(homeCategory, id);
        return ResponseEntity.ok(updateHC);
    }
}
