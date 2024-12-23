package org.example.service.busines.typeAssortement;

import lombok.*;
import org.example.dao.typeAssortementDAO.TypeAssortementDao;
import org.example.model.TypeAssortement;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAssortementServiceImpl implements TypeAssortementService {
    private TypeAssortementDao typeAssortementDao;

    @Override
    public List<TypeAssortement> getAllTypeAssortement() {
        return typeAssortementDao.findAll();
    }

    @Override
    public TypeAssortement getTypeAssortementById(int id) {
        List<TypeAssortement> typeAssortementsAll = getAllTypeAssortement();
        return typeAssortementsAll.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Тип ассортимента по id не найден..."));
    }

    @Override
    public TypeAssortement getTypeAssortementByName(String name) {
        List<TypeAssortement> typeAssortementsAll = getAllTypeAssortement();
        return typeAssortementsAll.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Тип + " + name + " не найден..."));
    }
}
