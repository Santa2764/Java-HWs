package com.example.CoffeeShop_Spring.model;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthdate;
    private String numTel;
    private String email;
    private int discount;
}
