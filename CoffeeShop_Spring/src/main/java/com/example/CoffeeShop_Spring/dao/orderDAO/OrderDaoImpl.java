package com.example.CoffeeShop_Spring.dao.orderDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.Order;
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
public class OrderDaoImpl implements OrderDao {
    private static final String FIND_ALL_ORDERS = "SELECT * FROM \"Order\"";
    private static final String SAVE_ORDER = """
        INSERT INTO \"Order\"(
            orderDate, idClient
        )
            VALUES(?, ?)
        """;
    private static final String UPDATE_ORDER = """
            UPDATE \"Order\" SET
                       orderDate = ?,
                       idClient = ?,
            WHERE \"Order\".id = ?
            """;
    private static final String DELETE_ALL_ORDERS = "DELETE FROM \"Order\"";
    private static final String DELETE_ORDER = "DELETE FROM \"Order\" WHERE \"Order\".id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrderRowMapper orderRowMapper;


    @Override
    public void save(Order order) {
        try {
            jdbcTemplate.update(SAVE_ORDER,
                    order.getOrderDate(),
                    order.getIdClient()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<Order> orders) {
        return jdbcTemplate.batchUpdate(SAVE_ORDER,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Order order = orders.get(i);
                        ps.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
                        ps.setInt(2, order.getIdClient());
                    }
                    @Override
                    public int getBatchSize() {
                        return orders.size();
                    }
                }
        );
    }

    @Override
    public void update(Order order) {
        try {
            jdbcTemplate.update(UPDATE_ORDER,
                    order.getOrderDate(),
                    order.getIdClient()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(Order order) {
        try {
            jdbcTemplate.update(DELETE_ORDER, order.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Order> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_ORDERS, orderRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_ORDERS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
