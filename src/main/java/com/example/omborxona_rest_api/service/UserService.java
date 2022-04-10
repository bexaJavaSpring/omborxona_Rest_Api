package com.example.omborxona_rest_api.service;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.dto.UsersDto;
import com.example.omborxona_rest_api.entity.Users;
import com.example.omborxona_rest_api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public ApiResponse saveUser(UsersDto dto) {
        ApiResponse apiResponse=new ApiResponse();
        for (Users users : usersRepository.findAll()) {
            if (users.getName().equals(dto.getName())) {
                return new ApiResponse("This user already exist!", false);
            }
            users.setName(dto.getName());
            users.setPassword(dto.getPassword());
            users.setRole(dto.getRole());
            users.setPhone(dto.getPhone());
            users.setEmail(dto.getEmail());
            Users save = usersRepository.save(users);
            apiResponse.setObject(save);
            break;
        }
        return new ApiResponse("Added", true);
    }
}
