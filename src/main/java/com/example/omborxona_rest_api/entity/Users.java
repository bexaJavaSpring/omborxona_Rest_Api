package com.example.omborxona_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String name;
    private String email;
    @Column(length = 8)
    private String password;
    private String role="USER";
    @Column(nullable = false,unique = true,length = 13)
    private String phone;
    private boolean active;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Warehouse> warehouseList;
}
