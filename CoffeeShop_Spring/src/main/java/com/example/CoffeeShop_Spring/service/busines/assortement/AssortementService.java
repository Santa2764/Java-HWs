package com.example.CoffeeShop_Spring.service.busines.assortement;

import com.example.CoffeeShop_Spring.model.Assortement;
import java.util.List;

public interface AssortementService {
    void addAssortement(Assortement assortement);
    List<Assortement> getAssortementsByType(String type);
    Assortement getAssortementById(int id);
    boolean deleteAssortementById(int id);
}
