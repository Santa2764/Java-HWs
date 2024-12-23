package com.example.CoffeeShop_Spring.service.busines.personalPosition;

import com.example.CoffeeShop_Spring.dao.personalPositionDAO.PersonalPositionDao;
import com.example.CoffeeShop_Spring.model.PersonalPosition;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class PersonalPositionServiceImpl implements PersonalPositionService {

    @Autowired
    private PersonalPositionDao personalPositionDaoDao;


    @Override
    public List<PersonalPosition> getAllPersonalPositions() {
        return personalPositionDaoDao.findAll();
    }

    @Override
    public PersonalPosition getPersonalPositionByName(String name) {
        List<PersonalPosition> personalPositionsAll = getAllPersonalPositions();
        return personalPositionsAll.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Позиция + " + name + " не найдена..."));
    }
}
