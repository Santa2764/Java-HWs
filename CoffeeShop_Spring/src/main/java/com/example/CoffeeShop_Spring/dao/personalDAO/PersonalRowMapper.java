package com.example.CoffeeShop_Spring.dao.personalDAO;

import com.example.CoffeeShop_Spring.model.Personal;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PersonalRowMapper implements RowMapper<Personal> {
    @Override
    public Personal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Personal personal = new Personal();
        personal.setId(rs.getInt("id"));
        personal.setName(rs.getString("name"));
        personal.setEmail(rs.getString("surname"));
        personal.setPatronymic(rs.getString("patronymic"));
        personal.setNumTel(rs.getString("numTel"));
        personal.setEmail(rs.getString("email"));
        personal.setIdPosition(rs.getInt("idPosition"));
        return personal;
    }
}
