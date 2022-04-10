package com.example.omborxona_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputDto {
    private Integer productId;
    private Integer supplierId;
    private Integer warehouseId;
   // private Integer currencyId;
    private String price;
    private String amount;
    private Date date;
    private String code;
    private String facture_number;
}
