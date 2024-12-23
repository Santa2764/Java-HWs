package com.example.CoffeeShop_Spring.dao.personalScheduleDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.PersonalSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonalScheduleDaoImpl implements PersonalScheduleDao {
    private static final String FIND_ALL_PERSONAL_SCHEDULES = "SELECT * FROM personalschedule";
    private static final String SAVE_PERSONAL_SCHEDULE = """
        INSERT INTO personalschedule(
            idPersonal, workDate, startTime, endTime
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_PERSONAL_SCHEDULE = """
            UPDATE personalschedule SET
                       idPersonal = ?,
                       workDate = ?,
                       startTime = ?,
                       endTime = ?
            WHERE personalschedule.id = ?
            """;
    private static final String DELETE_ALL_PERSONAL_SCHEDULES = "DELETE FROM personalschedule";
    private static final String DELETE_PERSONAL_SCHEDULE = "DELETE FROM personalschedule WHERE personalschedule.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PersonalScheduleRowMapper personalScheduleRowMapper;


    @Override
    public void save(PersonalSchedule personalSchedule) {
        try {
            jdbcTemplate.update(SAVE_PERSONAL_SCHEDULE,
                    personalSchedule.getIdPersonal(),
                    personalSchedule.getWorkDate(),
                    personalSchedule.getStartTime(),
                    personalSchedule.getEndTime()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<PersonalSchedule> personalSchedules) {
        return jdbcTemplate.batchUpdate(SAVE_PERSONAL_SCHEDULE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PersonalSchedule personalSchedule = personalSchedules.get(i);
                        ps.setInt(1, personalSchedule.getIdPersonal());
                        ps.setDate(2, java.sql.Date.valueOf(personalSchedule.getWorkDate()));
                        ps.setTime(3, java.sql.Time.valueOf(personalSchedule.getStartTime()));
                        ps.setTime(4, java.sql.Time.valueOf(personalSchedule.getEndTime()));
                    }
                    @Override
                    public int getBatchSize() {
                        return personalSchedules.size();
                    }
                }
        );
    }

    @Override
    public void update(PersonalSchedule personalSchedule) {
        try {
            jdbcTemplate.update(UPDATE_PERSONAL_SCHEDULE,
                    personalSchedule.getIdPersonal(),
                    personalSchedule.getWorkDate(),
                    personalSchedule.getStartTime(),
                    personalSchedule.getEndTime()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(PersonalSchedule personalSchedule) {
        try {
            jdbcTemplate.update(DELETE_PERSONAL_SCHEDULE, personalSchedule.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<PersonalSchedule> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_PERSONAL_SCHEDULES, personalScheduleRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_PERSONAL_SCHEDULES);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
