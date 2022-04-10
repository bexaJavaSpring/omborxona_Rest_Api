package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.InputDto;
import com.example.omborxona_rest_api.entity.*;
import com.example.omborxona_rest_api.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    final InputRepository inputRepository;
    final CurrencyRepository currencyRepository;
    final WarehouseRepository warehouseRepository;
    final InputProductRepository inputProductRepository;
    final ProductRepository productRepository;
    final SuplierRepository suplierRepository;

    public InputService(InputRepository inputRepository, CurrencyRepository currencyRepository, WarehouseRepository warehouseRepository, InputProductRepository inputProductRepository, ProductRepository productRepository, SuplierRepository suplierRepository) {
        this.inputRepository = inputRepository;
        this.currencyRepository = currencyRepository;
        this.warehouseRepository = warehouseRepository;
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
        this.suplierRepository = suplierRepository;
    }

    public ApiResponse all() {
        List<Input> all = inputRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(InputDto dto) {
        Optional<Warehouse> byId = warehouseRepository.findById(dto.getWarehouseId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        Optional<Product> byId2 = productRepository.findById(dto.getProductId());

        InputProduct inputProduct = new InputProduct();
        inputProduct.setPrice(dto.getPrice());
        inputProduct.setAmount(dto.getAmount());
        inputProduct.setProduct(byId2.get());

        Input input = new Input();
        input.setCode(dto.getCode());
        input.setFacture_number(dto.getFacture_number());
        input.setDate(dto.getDate());
        input.setWarehouse(byId.get());

        Optional<Suplier> byId1 = suplierRepository.findById(dto.getSupplierId());
        input.setSuplier(byId1.get());

//        Optional<Currency> byId2 = currencyRepository.findById(dto.getCurrencyId());
//        input.setCurrency(byId2.get());
        Input save5 = inputRepository.save(input);
        inputProduct.setInput(save5);
        InputProduct save3 = inputProductRepository.save(inputProduct);
        return new ApiResponse("Added", true, save3);
    }

    public ApiResponse edit(Integer id, InputDto dto) {
        Optional<Warehouse> byId = warehouseRepository.findById(dto.getWarehouseId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        Optional<InputProduct> byId1 = inputProductRepository.findById(id);
        if (!byId1.isPresent()) {
            return new ApiResponse("Not found",false,null);
        }
        InputProduct inputProduct = byId1.get();
        inputProduct.setPrice(dto.getPrice());
        inputProduct.setAmount(dto.getAmount());

        Optional<Product> byId3 = productRepository.findById(dto.getProductId());
        inputProduct.setProduct(byId3.get());

        Input input = inputProduct.getInput();
        input.setDate(dto.getDate());
        input.setCode(dto.getCode());
        input.setFacture_number(dto.getFacture_number());

        Optional<Suplier> byId2 = suplierRepository.findById(dto.getSupplierId());
        input.setSuplier(byId2.get());
        input.setWarehouse(byId.get());
        inputProduct.setInput(input);
        InputProduct save = inputProductRepository.save(inputProduct);
        return new ApiResponse("Edit", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found", false, null);
        }
        InputProduct save = byId.get();
        inputProductRepository.delete(save);
        return new ApiResponse("Delete", true, save);
    }
}
