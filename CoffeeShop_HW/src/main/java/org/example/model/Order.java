package org.example.model;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private int id;
    private LocalDate orderDate;
    private int idClient;
}
