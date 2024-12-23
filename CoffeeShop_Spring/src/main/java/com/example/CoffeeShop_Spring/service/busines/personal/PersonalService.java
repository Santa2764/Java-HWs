package com.example.CoffeeShop_Spring.service.busines.personal;

import com.example.CoffeeShop_Spring.model.Personal;
import java.util.List;

public interface PersonalService {
    void addPersonal(Personal personal);
    List<Personal> getPersonalsByPosition(String position);
    Personal getPersonalById(int id);
    void updatePersonalEmail(Personal personal, String email);
    void updatePersonalNumTel(Personal personal, String numTel);
    void updatePersonal(Personal personal);
    boolean deletePersonalById(int id);
}
