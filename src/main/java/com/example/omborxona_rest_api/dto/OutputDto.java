package com.example.omborxona_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDto {
   private Integer productId;
   private String price;
   private String amount;
   private Date date;
   private Long facture_number;
   //private Integer  currencyId;
   private Integer  clientId;
   private Integer  warehouseId;
}
