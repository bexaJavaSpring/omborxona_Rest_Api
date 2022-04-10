package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.WarehouseDto;
import com.example.omborxona_rest_api.repository.WarehouseRepository;
import com.example.omborxona_rest_api.service.adminservice.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(warehouseService.all());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody WarehouseDto warehouseDto){
        ApiResponse apiResponse= warehouseService.add(warehouseDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody WarehouseDto warehouseDto){
       ApiResponse apiResponse= warehouseService.edit(id,warehouseDto);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse= warehouseService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:404).body(apiResponse);
    }
}
