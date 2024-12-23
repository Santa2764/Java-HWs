package com.example.CoffeeShop_Spring.model;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalSchedule {
    private int id;
    private int idPersonal;
    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
