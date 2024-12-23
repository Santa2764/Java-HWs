package org.example.service.busines.assortement;

import lombok.*;
import org.example.dao.assortementDAO.AssortementDao;
import org.example.dao.personalPositionDAO.PersonalPositionDaoImpl;
import org.example.dao.typeAssortementDAO.TypeAssortementDaoImpl;
import org.example.model.Assortement;
import org.example.model.PersonalPosition;
import org.example.model.TypeAssortement;
import org.example.service.busines.personalPosition.PersonalPositionService;
import org.example.service.busines.personalPosition.PersonalPositionServiceImpl;
import org.example.service.busines.typeAssortement.TypeAssortementService;
import org.example.service.busines.typeAssortement.TypeAssortementServiceImpl;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssortementServiceImpl implements AssortementService {
    private AssortementDao assortementDao;

    @Override
    public void addAssortement(Assortement assortement) {
        assortementDao.save(assortement);
    }

    @Override
    public List<Assortement> getAssortementsByType(String type) {
        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(new TypeAssortementDaoImpl());
        TypeAssortement typeAssortement = typeAssortementService.getTypeAssortementByName(type);
        return assortementDao.getAssortementsByType(typeAssortement.getId());
    }

    @Override
    public Assortement getAssortementById(int id) {
        return assortementDao.getAssortementById(id);
    }

    @Override
    public boolean deleteAssortementById(int id) {
        Assortement assortement = assortementDao.getAssortementById(id);
        if (assortement != null) {
            assortementDao.delete(assortement);
            return true;
        }
        return false;
    }
}
