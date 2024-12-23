package com.example.CoffeeShop_Spring.dao.assortementDAO;

import com.example.CoffeeShop_Spring.dao.CRUDInterface;
import com.example.CoffeeShop_Spring.model.Assortement;

import java.util.List;

public interface AssortementDao extends CRUDInterface<Assortement> {
    List<Assortement> getAssortementsByType(int idType);
    Assortement getAssortementById(int id);
}
