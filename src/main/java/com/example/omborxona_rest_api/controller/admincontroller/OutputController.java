package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.OutputDto;
import com.example.omborxona_rest_api.entity.Output;
import com.example.omborxona_rest_api.repository.OutputRepository;
import com.example.omborxona_rest_api.service.adminservice.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputService outputService;
    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        List<Output> all = outputRepository.findAll();
       return ResponseEntity.ok().body(all);
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody OutputDto dto) {
       ApiResponse apiResponse= outputService.add(dto);
       return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody OutputDto dto) {
       ApiResponse apiResponse= outputService.edit(id,dto);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
       ApiResponse apiResponse= outputService.delete(id);
       return ResponseEntity.status(apiResponse.isSuccess()?204:404).body(apiResponse);
    }
}
