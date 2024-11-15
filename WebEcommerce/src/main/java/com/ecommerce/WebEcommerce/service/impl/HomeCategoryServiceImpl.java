package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.model.HomeCategory;
import com.ecommerce.WebEcommerce.repository.HomeCategoryRepository;
import com.ecommerce.WebEcommerce.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeCategoryServiceImpl implements HomeCategoryService {
    private final HomeCategoryRepository homeCategoryRepository;
    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createHomeCategories(List<HomeCategory> homeCategories) {
        if (homeCategoryRepository.findAll().isEmpty()){
            return homeCategoryRepository.saveAll(homeCategories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(()-> new Exception("Khong tim thay danh muc nay"));
        if (existingCategory.getImage()!=null){
            existingCategory.setImage(homeCategory.getImage());
        }
        if(existingCategory.getCategoryId()!=null){
            existingCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
