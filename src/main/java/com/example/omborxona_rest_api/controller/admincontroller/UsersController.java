package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.UsersDto;
import com.example.omborxona_rest_api.entity.Users;
import com.example.omborxona_rest_api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<Users> all = usersRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody UsersDto dto){
        ApiResponse  apiResponse=new ApiResponse();
        for (Users users : usersRepository.findAll()) {
            if(users.getName().equals(dto.getName())){
               apiResponse.setMessage("This user already exist");
               apiResponse.setSuccess(false);
            }
        }
        Users users=new Users();
        users.setName(dto.getName());
        users.setActive(dto.isActive());
        users.setEmail(dto.getEmail());
        users.setPhone(dto.getPhone());
        users.setPassword(dto.getPassword());
        Users save=usersRepository.save(users);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> block(@PathVariable Integer id){
        ApiResponse apiResponse=new ApiResponse();
        Optional<Users> byId = usersRepository.findById(id);
        if (!byId.isPresent()) {
            apiResponse.setMessage("Not found");
            apiResponse.setSuccess(false);
        }
        Users users=byId.get();
        users.setActive(false);
        apiResponse.setMessage("Edited");
        apiResponse.setSuccess(true);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


}
