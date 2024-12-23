package org.example.service.busines.personal;

import lombok.*;

import org.example.dao.personalDAO.PersonalDao;
import org.example.dao.personalPositionDAO.PersonalPositionDaoImpl;
import org.example.model.Personal;
import org.example.model.PersonalPosition;
import org.example.service.busines.personalPosition.PersonalPositionService;
import org.example.service.busines.personalPosition.PersonalPositionServiceImpl;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalServiceImpl implements PersonalService {
    private PersonalDao personalDao;

    @Override
    public void addPersonal(Personal personal) {
        personalDao.save(personal);
    }

    @Override
    public List<Personal> getPersonalsByPosition(String position) {
        PersonalPositionService personalPositionService = new PersonalPositionServiceImpl(new PersonalPositionDaoImpl());
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
