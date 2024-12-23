package org.example.dao.assortementDAO;

import org.example.dao.CRUDInterface;
import org.example.model.Assortement;

import java.util.List;

public interface AssortementDao extends CRUDInterface<Assortement> {
    List<Assortement> getAssortementsByType(int idType);
    Assortement getAssortementById(int id);
}
