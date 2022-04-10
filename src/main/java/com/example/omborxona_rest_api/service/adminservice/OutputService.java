package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.OutputDto;
import com.example.omborxona_rest_api.entity.*;
import com.example.omborxona_rest_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse add(OutputDto dto) {
        Optional<Warehouse> byId = warehouseRepository.findById(dto.getWarehouseId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        Optional<Product> byId2 = productRepository.findById(dto.getProductId());
        OutProduct outProduct = new OutProduct();
        outProduct.setPrice(dto.getPrice());
        outProduct.setAmount(dto.getAmount());
        outProduct.setProduct(byId2.get());

        Output output = new Output();
        output.setDate(dto.getDate());
        output.setFacture_number(dto.getFacture_number());
        output.setWarehouse(byId.get());

        Optional<Client> byId1 = clientRepository.findById(dto.getClientId());
        output.setClient(byId1.get());

//        Optional<Currency> byId2 = currencyRepository.findById(dto.getCurrencyId());
//        output.setCurrency(byId2.get());

        Output save4 = outputRepository.save(output);
        outProduct.setOutput(save4);
        OutProduct save5 = outputProductRepository.save(outProduct);
        return new ApiResponse("Added", true, save5);
    }

    public ApiResponse edit(Integer id, OutputDto dto) {
        Optional<Warehouse> byId = warehouseRepository.findById(dto.getWarehouseId());
        if (!byId.isPresent()) {
            return new ApiResponse("Xatolik", false);
        }
        Optional<OutProduct> byId1 = outputProductRepository.findById(id);
        if (!byId1.isPresent()) {
            return new ApiResponse("Not found",false,null);
        }
        OutProduct outProduct = byId1.get();
        outProduct.setPrice(dto.getPrice());
        outProduct.setAmount(dto.getAmount());

        Optional<Product> byId3 = productRepository.findById(dto.getProductId());
        outProduct.setProduct(byId3.get());

        Output output = outProduct.getOutput();
        output.setDate(dto.getDate());
        output.setFacture_number(dto.getFacture_number());
        output.setWarehouse(byId.get());

        Optional<Client> byId2 = clientRepository.findById(dto.getClientId());
        output.setClient(byId2.get());

//        Optional<Currency> byId3 = currencyRepository.findById(dto.getCurrencyId());
//        output.setCurrency(byId3.get());

        outProduct.setOutput(output);
        OutProduct save = outputProductRepository.save(outProduct);
        return new ApiResponse("Edit",true,save);
    }

    public ApiResponse delete(Integer id) {
        Optional<OutProduct> byId = outputProductRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        OutProduct outProduct = byId.get();
        outputProductRepository.delete(outProduct);
        return new ApiResponse("Delete", true, outProduct);
    }
}
