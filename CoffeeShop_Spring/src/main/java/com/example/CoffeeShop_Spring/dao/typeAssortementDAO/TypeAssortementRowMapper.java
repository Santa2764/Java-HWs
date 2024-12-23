package com.example.CoffeeShop_Spring.dao.typeAssortementDAO;

import com.example.CoffeeShop_Spring.model.TypeAssortement;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class TypeAssortementRowMapper implements RowMapper<TypeAssortement> {
    @Override
    public TypeAssortement mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypeAssortement type = new TypeAssortement();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    }
}
