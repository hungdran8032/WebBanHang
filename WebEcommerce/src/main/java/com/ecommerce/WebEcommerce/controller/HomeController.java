package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HelloWorld(){
        ApiResponse api = new ApiResponse();
        api.setMessage("Hello! WellCome to my web ecommerce");
        return api;
    }
}
