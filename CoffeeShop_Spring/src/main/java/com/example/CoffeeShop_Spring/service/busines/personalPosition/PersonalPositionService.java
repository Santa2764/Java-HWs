package com.example.CoffeeShop_Spring.service.busines.personalPosition;

import com.example.CoffeeShop_Spring.model.PersonalPosition;
import java.util.List;

public interface PersonalPositionService {
    List<PersonalPosition> getAllPersonalPositions();
    PersonalPosition getPersonalPositionByName(String name);
}
