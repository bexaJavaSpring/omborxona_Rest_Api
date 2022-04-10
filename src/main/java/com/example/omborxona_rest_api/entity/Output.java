package com.example.omborxona_rest_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private Long facture_number=new Random().nextLong(1000000000000L, 10000000000000L);;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Client client;
}
