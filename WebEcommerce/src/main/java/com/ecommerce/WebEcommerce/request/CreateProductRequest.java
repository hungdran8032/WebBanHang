package com.ecommerce.WebEcommerce.request;


import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {
    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private String color;
    private List<String> images;
    private String category1, category2, category3;
    private String size;
}
