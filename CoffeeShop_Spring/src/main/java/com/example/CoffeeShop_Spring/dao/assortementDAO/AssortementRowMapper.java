package com.example.CoffeeShop_Spring.dao.assortementDAO;

import com.example.CoffeeShop_Spring.model.Assortement;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AssortementRowMapper implements RowMapper<Assortement> {
    @Override
    public Assortement mapRow(ResultSet rs, int rowNum) throws SQLException {
        Assortement assortement = new Assortement();
        assortement.setId(rs.getInt("id"));
        assortement.setNameEn(rs.getString("nameEn"));
        assortement.setNameRu(rs.getString("nameRu"));
        assortement.setPrice(rs.getDouble("price"));
        assortement.setIdTypeAssortement(rs.getInt("idType"));
        return assortement;
    }
}
