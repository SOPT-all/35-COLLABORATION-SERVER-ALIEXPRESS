package com.example.aliexpress.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;
    private String categoryName;

    public CategoryEntity() {}

    public long getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
}
