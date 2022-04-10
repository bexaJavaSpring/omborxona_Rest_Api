package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.CurrencyDto;
import com.example.omborxona_rest_api.repository.CurrencyRepository;
import com.example.omborxona_rest_api.service.adminservice.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;
     @Autowired
    CurrencyService currencyService;

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(currencyService.all());
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody CurrencyDto currencyDto) {
       ApiResponse apiResponse= currencyService.add(currencyDto);
       return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody CurrencyDto dto){
       ApiResponse apiResponse= currencyService.edit(id,dto);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
       ApiResponse apiResponse= currencyService.delete(id);
       return ResponseEntity.status(apiResponse.isSuccess()?204:404).body(apiResponse);
    }
}
