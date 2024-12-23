package com.example.CoffeeShop_Spring.dao.personalScheduleDAO;

import com.example.CoffeeShop_Spring.model.PersonalSchedule;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PersonalScheduleRowMapper implements RowMapper<PersonalSchedule> {
    @Override
    public PersonalSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonalSchedule personalSchedule = new PersonalSchedule();
        personalSchedule.setId(rs.getInt("id"));
        personalSchedule.setIdPersonal(rs.getInt("idPersonal"));
        personalSchedule.setWorkDate(rs.getDate("workDate").toLocalDate());
        personalSchedule.setStartTime(rs.getTime("startTime").toLocalTime());
        personalSchedule.setEndTime(rs.getTime("endTime").toLocalTime());
        return personalSchedule;
    }
}
