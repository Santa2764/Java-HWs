package com.example.CoffeeShop_Spring.dao.typeAssortementDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.TypeAssortement;
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
public class TypeAssortementDaoImpl implements TypeAssortementDao {
    private static final String FIND_ALL_TYPE_ASSORTEMENTS = "SELECT * FROM typeassortement";
    private static final String SAVE_TYPE_ASSORTEMENT = """
        INSERT INTO typeassortement(
            name
        )
            VALUES(?)
        """;
    private static final String UPDATE_TYPE_ASSORTEMENT = """
            UPDATE typeassortement SET
                       name = ?
            WHERE typeassortement.id = ?
            """;
    private static final String DELETE_ALL_TYPE_ASSORTEMENTS = "DELETE FROM typeassortement";
    private static final String DELETE_TYPE_ASSORTEMENT = "DELETE FROM typeassortement WHERE typeassortement.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TypeAssortementRowMapper typeAssortementRowMapper;


    @Override
    public void save(TypeAssortement typeAssortement) {
        try {
            jdbcTemplate.update(SAVE_TYPE_ASSORTEMENT,
                    typeAssortement.getName()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<TypeAssortement> typeAssortements) {
        return jdbcTemplate.batchUpdate(SAVE_TYPE_ASSORTEMENT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        TypeAssortement typeAssortement = typeAssortements.get(i);
                        ps.setString(1, typeAssortement.getName());
                    }
                    @Override
                    public int getBatchSize() {
                        return typeAssortements.size();
                    }
                }
        );
    }

    @Override
    public void update(TypeAssortement typeAssortement) {
        try {
            jdbcTemplate.update(UPDATE_TYPE_ASSORTEMENT,
                    typeAssortement.getName()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(TypeAssortement typeAssortement) {
        try {
            jdbcTemplate.update(DELETE_TYPE_ASSORTEMENT, typeAssortement.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<TypeAssortement> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_TYPE_ASSORTEMENTS, typeAssortementRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_TYPE_ASSORTEMENTS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
