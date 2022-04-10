package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.ProductDto;
import com.example.omborxona_rest_api.entity.*;
import com.example.omborxona_rest_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse all() {
        List<Product> all = productRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(ProductDto productDto) {
        Optional<Category> byId = categoryRepository.findById(productDto.getCategoryId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        if (productRepository.existsByNameIgnoreCase(productDto.getName())) {
            return new ApiResponse("Bunaqa name lik product bor", false);
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(byId.get());
        Optional<Attachment> byId1 = attachmentRepository.findById(productDto.getAttachmentId());
        product.setAttachment(byId1.get());
        Optional<Measurement> byId2 = measurementRepository.findById(productDto.getMeasurementId());
        product.setMeasurement(byId2.get());
        Product save4 = productRepository.save(product);
        return new ApiResponse("Added", true, save4);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        return new ApiResponse("Mana", true, byId.get());
    }

    public ApiResponse edit(Integer id, ProductDto dto) {
        Optional<Category> byId = categoryRepository.findById(dto.getCategoryId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        if (productRepository.existsByNameIgnoreCase(dto.getName())) {
            return new ApiResponse("Bunaqa name lik product bor", false);
        }
        Optional<Product> byId1 = productRepository.findById(id);
        Product product = byId1.get();
        product.setName(dto.getName());
        product.setCategory(byId.get());
        Product save = productRepository.save(product);
        return new ApiResponse("Edit", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Product> byId1 = productRepository.findById(id);
        if (!byId1.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        productRepository.delete(byId1.get());
        return new ApiResponse("Delete", true);
    }

}
