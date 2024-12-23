package com.example.CoffeeShop_Spring.dao.assortementDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.Assortement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssortementDaoImpl implements AssortementDao {
    private static final String FIND_ALL_ASSORTEMENTS = "SELECT * FROM assortement";
    private static final String SAVE_ASSORTEMENT = """
        INSERT INTO assortement(
            nameEn, nameRu, price, idType
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_ASSORTEMENT = """
            UPDATE assortement SET
                       nameEn = ?,
                       nameRu = ?,
                       price = ?,
                       idType = ? 
            WHERE assortement.id = ?
            """;
    private static final String DELETE_ALL_ASSORTEMENTS = "DELETE FROM assortement";
    private static final String DELETE_ASSORTEMENT = "DELETE FROM assortement WHERE assortement.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Assortement> assortementRowMapper;


    @Override
    public List<Assortement> getAssortementsByType(int idType) {
        List<Assortement> assortements = findAll();
        return assortements.stream()
                .filter(a -> a.getIdTypeAssortement() == idType)
                .toList();
    }

    @Override
    public Assortement getAssortementById(int id) {
        List<Assortement> assortements = findAll();
        return assortements.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void save(Assortement assortement) {
        try {
            jdbcTemplate.update(SAVE_ASSORTEMENT,
                    assortement.getNameEn(),
                    assortement.getNameRu(),
                    assortement.getPrice(),
                    assortement.getIdTypeAssortement()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<Assortement> assortements) {
        return jdbcTemplate.batchUpdate(SAVE_ASSORTEMENT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Assortement assortement = assortements.get(i);
                        ps.setString(1, assortement.getNameEn());
                        ps.setString(2, assortement.getNameRu());
                        ps.setDouble(3, assortement.getPrice());
                        ps.setInt(4, assortement.getIdTypeAssortement());
                    }
                    @Override
                    public int getBatchSize() {
                        return assortements.size();
                    }
                }
        );
    }

    @Override
    public void update(Assortement assortement) {
        try {
            jdbcTemplate.update(UPDATE_ASSORTEMENT,
                    assortement.getNameEn(),
                    assortement.getNameRu(),
                    assortement.getPrice(),
                    assortement.getIdTypeAssortement()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(Assortement assortement) {
        try {
            jdbcTemplate.update(DELETE_ASSORTEMENT, assortement.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Assortement> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_ASSORTEMENTS, assortementRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_ASSORTEMENTS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
