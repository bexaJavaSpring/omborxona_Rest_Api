package com.example.omborxona_rest_api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    private boolean active;

    public Warehouse(String name, boolean active) {
        this.name = name;
        this.active = active;
    }
}
