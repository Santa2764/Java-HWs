package com.example.CoffeeShop_Spring.dao.personalPositionDAO;

import com.example.CoffeeShop_Spring.model.PersonalPosition;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PersonalPositionRowMapper implements RowMapper<PersonalPosition> {
    @Override
    public PersonalPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonalPosition personalPosition = new PersonalPosition();
        personalPosition.setId(rs.getInt("id"));
        personalPosition.setName(rs.getString("name"));
        return personalPosition;
    }
}
