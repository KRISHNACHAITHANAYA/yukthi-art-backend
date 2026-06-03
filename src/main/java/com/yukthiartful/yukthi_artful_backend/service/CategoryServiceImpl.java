package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.entity.Category;
import com.yukthiartful.yukthi_artful_backend.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}