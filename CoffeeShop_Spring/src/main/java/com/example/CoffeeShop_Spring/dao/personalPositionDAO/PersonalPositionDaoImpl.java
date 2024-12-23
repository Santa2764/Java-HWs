package com.example.CoffeeShop_Spring.dao.personalPositionDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.PersonalPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonalPositionDaoImpl implements PersonalPositionDao {
    private static final String FIND_ALL_TYPE_POSITIONS = "SELECT * FROM personalposition";
    private static final String SAVE_TYPE_POSITION = """
        INSERT INTO personalposition(
            name
        )
            VALUES(?)
        """;
    private static final String UPDATE_TYPE_POSITION = """
            UPDATE personalposition SET
                       name = ?
            WHERE personalposition.id = ?
            """;
    private static final String DELETE_ALL_TYPE_POSITIONS = "DELETE FROM personalposition";
    private static final String DELETE_TYPE_POSITION = "DELETE FROM personalposition WHERE personalposition.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<PersonalPosition> personalPositionRowMapper;


    @Override
    public void save(PersonalPosition personalPosition) {
        try {
            jdbcTemplate.update(SAVE_TYPE_POSITION,
                    personalPosition.getName()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<PersonalPosition> personalPositions) {
        return jdbcTemplate.batchUpdate(SAVE_TYPE_POSITION,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PersonalPosition personalPosition = personalPositions.get(i);
                        ps.setString(1, personalPosition.getName());
                    }
                    @Override
                    public int getBatchSize() {
                        return personalPositions.size();
                    }
                }
        );
    }

    @Override
    public void update(PersonalPosition personalPosition) {
        try {
            jdbcTemplate.update(UPDATE_TYPE_POSITION,
                    personalPosition.getName()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(PersonalPosition personalPosition) {
        try {
            jdbcTemplate.update(DELETE_TYPE_POSITION, personalPosition.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<PersonalPosition> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_TYPE_POSITIONS, personalPositionRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_TYPE_POSITIONS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
