package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.CategoryDto;
import com.example.omborxona_rest_api.repository.CategoryRepository;
import com.example.omborxona_rest_api.service.adminservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(categoryService.all());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse= categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse= categoryService.edit(id,categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

}
