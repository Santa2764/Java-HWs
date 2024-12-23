package org.example.service.busines.personalPosition;

import lombok.*;
import org.example.dao.personalPositionDAO.PersonalPositionDao;
import org.example.model.PersonalPosition;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalPositionServiceImpl implements PersonalPositionService {
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
