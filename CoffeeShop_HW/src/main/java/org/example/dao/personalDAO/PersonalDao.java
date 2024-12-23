package org.example.dao.personalDAO;

import org.example.dao.CRUDInterface;
import org.example.model.Personal;

import java.util.List;

public interface PersonalDao extends CRUDInterface<Personal> {
    List<Personal> getPersonalsByPosition(int idPosition);
    Personal getPersonalById(int id);
}
