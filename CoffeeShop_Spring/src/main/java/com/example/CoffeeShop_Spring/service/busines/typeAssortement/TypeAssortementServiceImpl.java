package com.example.CoffeeShop_Spring.service.busines.typeAssortement;

import com.example.CoffeeShop_Spring.dao.typeAssortementDAO.TypeAssortementDao;
import com.example.CoffeeShop_Spring.model.TypeAssortement;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TypeAssortementServiceImpl implements TypeAssortementService {

    @Autowired
    private TypeAssortementDao typeAssortementDao;


    @Override
    public List<TypeAssortement> getAllTypeAssortement() {
        return typeAssortementDao.findAll();
    }

    @Override
    public TypeAssortement getTypeAssortementById(int id) {
        List<TypeAssortement> typeAssortementsAll = getAllTypeAssortement();
        return typeAssortementsAll.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Тип ассортимента по id не найден..."));
    }

    @Override
    public TypeAssortement getTypeAssortementByName(String name) {
        List<TypeAssortement> typeAssortementsAll = getAllTypeAssortement();
        return typeAssortementsAll.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Тип + " + name + " не найден..."));
    }
}
