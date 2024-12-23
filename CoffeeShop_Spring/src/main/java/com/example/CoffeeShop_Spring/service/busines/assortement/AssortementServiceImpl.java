package com.example.CoffeeShop_Spring.service.busines.assortement;

import com.example.CoffeeShop_Spring.dao.assortementDAO.AssortementDao;
import com.example.CoffeeShop_Spring.model.Assortement;
import com.example.CoffeeShop_Spring.model.TypeAssortement;
import com.example.CoffeeShop_Spring.service.busines.typeAssortement.TypeAssortementService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class AssortementServiceImpl implements AssortementService {

    @Autowired
    private TypeAssortementService typeAssortementService;

    @Autowired
    private AssortementDao assortementDao;

    @Override
    public void addAssortement(Assortement assortement) {
        assortementDao.save(assortement);
    }

    @Override
    public List<Assortement> getAssortementsByType(String type) {
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
