package com.example.omborxona_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {
    private String name;
    private String phone;
    private String role;
    private String email;
    private String password;
    private boolean active;
    public UsersDto(String phone, String email, String password) {
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public UsersDto(String name) {
        this.name = name;
    }
}
