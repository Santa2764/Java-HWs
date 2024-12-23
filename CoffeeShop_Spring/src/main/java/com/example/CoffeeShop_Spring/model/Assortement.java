package com.example.CoffeeShop_Spring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assortement {
    private int id;
    private String nameEn;
    private String nameRu;
    private double price;
    private int idTypeAssortement;
}
