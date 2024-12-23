package com.example.CoffeeShop_Spring.dao.personalDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.Personal;
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
public class PersonalDaoImpl implements PersonalDao {
    private static final String FIND_ALL_PERSONALS = "SELECT * FROM personal";
    private static final String SAVE_PERSONAL = """
        INSERT INTO personal(
            name, surname, patronymic, numTel, email, idPosition
        )
            VALUES(?, ?, ?, ?, ?, ?)
        """;
    private static final String UPDATE_PERSONAL = """
            UPDATE personal SET
                       name = ?,
                       surname = ?,
                       patronymic = ?,
                       numTel = ?,
                       email = ?,
                       idPosition = ?
            WHERE personal.id = ?
            """;
    private static final String DELETE_ALL_PERSONALS = "DELETE FROM personal";
    private static final String DELETE_PERSONAL = "DELETE FROM personal WHERE personal.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PersonalRowMapper personalRowMapper;


    @Override
    public List<Personal> getPersonalsByPosition(int idPosition) {
        List<Personal> personals = findAll();
        return personals.stream()
                .filter(p -> p.getIdPosition() == idPosition)
                .toList();
    }

    @Override
    public Personal getPersonalById(int id) {
        List<Personal> personals = findAll();
        return personals.stream()
                .filter(personal -> personal.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void save(Personal personal) {
        try {
            jdbcTemplate.update(SAVE_PERSONAL,
                    personal.getName(),
                    personal.getSurname(),
                    personal.getPatronymic(),
                    personal.getNumTel(),
                    personal.getEmail(),
                    personal.getIdPosition()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<Personal> personals) {
        return jdbcTemplate.batchUpdate(SAVE_PERSONAL,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Personal personal = personals.get(i);
                        ps.setString(1, personal.getName());
                        ps.setString(2, personal.getSurname());
                        ps.setString(3, personal.getPatronymic());
                        ps.setString(4, personal.getNumTel());
                        ps.setString(5, personal.getEmail());
                        ps.setInt(6, personal.getIdPosition());
                    }
                    @Override
                    public int getBatchSize() {
                        return personals.size();
                    }
                }
        );
    }

    @Override
    public void update(Personal personal) {
        try {
            jdbcTemplate.update(UPDATE_PERSONAL,
                    personal.getName(),
                    personal.getSurname(),
                    personal.getPatronymic(),
                    personal.getNumTel(),
                    personal.getEmail(),
                    personal.getIdPosition()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(Personal personal) {
        try {
            jdbcTemplate.update(DELETE_PERSONAL, personal.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Personal> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_PERSONALS, personalRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_PERSONALS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
