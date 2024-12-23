package com.example.CoffeeShop_Spring.service.busines.typeAssortement;

import com.example.CoffeeShop_Spring.model.TypeAssortement;

import java.util.List;

public interface TypeAssortementService {
    List<TypeAssortement> getAllTypeAssortement();
    TypeAssortement getTypeAssortementById(int id);
    TypeAssortement getTypeAssortementByName(String name);
}
