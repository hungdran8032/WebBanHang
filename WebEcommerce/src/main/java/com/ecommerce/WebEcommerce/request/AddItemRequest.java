package com.ecommerce.WebEcommerce.request;

import lombok.Data;

@Data
public class AddItemRequest {
    private Long idProduct;
    private String size;
    private int quantity;
}
