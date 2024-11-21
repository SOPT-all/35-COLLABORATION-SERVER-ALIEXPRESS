package com.example.aliexpress.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductList {
    @JsonProperty
    private List<Product> products;

    public ProductList(List<Product> products) {
        this.products = products;
    }
}
