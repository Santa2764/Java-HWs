package org.example.service.busines.personalPosition;

import org.example.model.PersonalPosition;

import java.util.List;

public interface PersonalPositionService {
    List<PersonalPosition> getAllPersonalPositions();
    PersonalPosition getPersonalPositionByName(String name);
}
