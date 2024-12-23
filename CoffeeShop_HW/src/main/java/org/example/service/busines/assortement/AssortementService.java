package org.example.service.busines.assortement;

import org.example.model.Assortement;

import java.util.List;

public interface AssortementService {
    void addAssortement(Assortement assortement);
    List<Assortement> getAssortementsByType(String type);
    Assortement getAssortementById(int id);
    boolean deleteAssortementById(int id);
}
