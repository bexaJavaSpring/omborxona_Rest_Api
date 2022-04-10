package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.CategoryDto;
import com.example.omborxona_rest_api.entity.Category;
import com.example.omborxona_rest_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse all() {
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(CategoryDto categoryDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryDto.getName())) {
            return new ApiResponse("Xatolik", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        Category save = categoryRepository.save(category);
        return new ApiResponse("Added!", true, save);
    }

    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        if (categoryRepository.existsByNameIgnoreCase(categoryDto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        Category save = categoryRepository.save(category);
        return new ApiResponse("Edited!", true, save);
    }

    public ApiResponse getById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return new ApiResponse("Show", true, byId);
    }

    public ApiResponse deleteCategoryById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        category.setActive(false);
        categoryRepository.save(category);
        return new ApiResponse("Deleted", true);
    }
}
