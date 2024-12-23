package com.example.CoffeeShop_Spring.service.busines.personal;

import com.example.CoffeeShop_Spring.dao.personalDAO.PersonalDao;
import com.example.CoffeeShop_Spring.model.Personal;
import com.example.CoffeeShop_Spring.model.PersonalPosition;
import com.example.CoffeeShop_Spring.service.busines.personalPosition.PersonalPositionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    PersonalPositionService personalPositionService;

    @Autowired
    private PersonalDao personalDao;


    @Override
    public void addPersonal(Personal personal) {
        personalDao.save(personal);
    }

    @Override
    public List<Personal> getPersonalsByPosition(String position) {
        PersonalPosition personalPosition = personalPositionService.getPersonalPositionByName(position);
        return personalDao.getPersonalsByPosition(personalPosition.getId());
    }

    @Override
    public Personal getPersonalById(int id) {
        return personalDao.getPersonalById(id);
    }

    @Override
    public void updatePersonalEmail(Personal personal, String email) {
        personal.setEmail(email);
        updatePersonal(personal);
    }

    @Override
    public void updatePersonalNumTel(Personal personal, String numTel) {
        personal.setNumTel(numTel);
        updatePersonal(personal);
    }

    @Override
    public void updatePersonal(Personal personal) {
        personalDao.update(personal);
    }

    @Override
    public boolean deletePersonalById(int id) {
        Personal personal = personalDao.getPersonalById(id);
        if (personal != null) {
            personalDao.delete(personal);
            return true;
        }
        return false;
    }
}
