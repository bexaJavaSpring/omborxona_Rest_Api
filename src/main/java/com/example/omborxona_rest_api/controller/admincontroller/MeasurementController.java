package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.MeausurementDto;
import com.example.omborxona_rest_api.repository.MeasurementRepository;
import com.example.omborxona_rest_api.service.adminservice.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    MeasurementService measurementService;
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(measurementService.all());
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid  @RequestBody MeausurementDto meausurementDto){
        ApiResponse apiResponse= measurementService.add(meausurementDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody MeausurementDto dto){
        ApiResponse apiResponse= measurementService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse= measurementService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?204:404).body(apiResponse);
    }
}
