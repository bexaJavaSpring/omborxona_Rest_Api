package com.example.omborxona_rest_api.service.adminservice;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.WarehouseDto;
import com.example.omborxona_rest_api.entity.Warehouse;
import com.example.omborxona_rest_api.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse all() {
        List<Warehouse> all = warehouseRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(WarehouseDto warehouseDto) {
        if (warehouseRepository.existsByNameIgnoreCase(warehouseDto.getName())) {
            return new ApiResponse("Xatolik", false);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(true);
        Warehouse save = warehouseRepository.save(warehouse);
        return new ApiResponse("Added!", true, save);
    }

    public ApiResponse edit(Integer id, WarehouseDto warehouseDto) {
        if (warehouseRepository.existsByNameIgnoreCase(warehouseDto.getName())) {
            return new ApiResponse("Bunday nom bor", false);
        }
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        Warehouse warehouse = byId.get();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(true);
        Warehouse save = warehouseRepository.save(warehouse);
        return new ApiResponse("Edit!", true, save);
    }

    public ApiResponse delete(Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        warehouseRepository.delete(byId.get());
        return new ApiResponse("Delete", true,byId.get());
    }
}
