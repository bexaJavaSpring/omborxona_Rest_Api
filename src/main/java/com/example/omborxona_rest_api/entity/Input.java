package com.example.omborxona_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @Column(unique = true)
    private String code;
    @Column(length = 20)
    private String facture_number;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Suplier suplier;
}
