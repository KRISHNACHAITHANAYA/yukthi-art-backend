package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import com.yukthiartful.yukthi_artful_backend.entity.Category;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> getAllCategories();
} 