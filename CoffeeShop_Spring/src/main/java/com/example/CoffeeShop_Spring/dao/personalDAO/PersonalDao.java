package com.example.CoffeeShop_Spring.dao.personalDAO;

import com.example.CoffeeShop_Spring.dao.CRUDInterface;
import com.example.CoffeeShop_Spring.model.Personal;

import java.util.List;

public interface PersonalDao extends CRUDInterface<Personal> {
    List<Personal> getPersonalsByPosition(int idPosition);
    Personal getPersonalById(int id);
}
