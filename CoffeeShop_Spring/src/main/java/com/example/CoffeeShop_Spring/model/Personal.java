package com.example.CoffeeShop_Spring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String numTel;
    private String email;
    private int idPosition;
}
