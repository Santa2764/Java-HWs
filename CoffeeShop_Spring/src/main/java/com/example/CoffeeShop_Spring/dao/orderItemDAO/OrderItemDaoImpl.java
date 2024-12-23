package com.example.CoffeeShop_Spring.dao.orderItemDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.model.OrderItem;
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
public class OrderItemDaoImpl implements OrderItemDao {
    private static final String FIND_ALL_ORDER_ITEMS = "SELECT * FROM orderitem";
    private static final String SAVE_ORDER_ITEM = """
        INSERT INTO orderitem(
            idOrder, idAssortement, quantity, price
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_ORDER_ITEM = """
            UPDATE orderitem SET
                       idOrder = ?,
                       idAssortement = ?,
                       quantity = ?,
                       price = ?
            WHERE orderitem.id = ?
            """;
    private static final String DELETE_ALL_ORDER_ITEMS = "DELETE FROM orderitem";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM orderitem WHERE orderitem.id = ?";

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrderItemRowMapper orderItemRowMapper;


    @Override
    public void save(OrderItem orderItem) {
        try {
            jdbcTemplate.update(SAVE_ORDER_ITEM,
                    orderItem.getOrderId(),
                    orderItem.getIdAssortement(),
                    orderItem.getQuantity(),
                    orderItem.getPrice()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<OrderItem> orderItems) {
        return jdbcTemplate.batchUpdate(SAVE_ORDER_ITEM,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        OrderItem orderItem = orderItems.get(i);
                        ps.setInt(1, orderItem.getOrderId());
                        ps.setInt(2, orderItem.getIdAssortement());
                        ps.setInt(3, orderItem.getQuantity());
                        ps.setDouble(4, orderItem.getPrice());
                    }
                    @Override
                    public int getBatchSize() {
                        return orderItems.size();
                    }
                }
        );
    }

    @Override
    public void update(OrderItem orderItem) {
        try {
            jdbcTemplate.update(UPDATE_ORDER_ITEM,
                    orderItem.getOrderId(),
                    orderItem.getIdAssortement(),
                    orderItem.getQuantity(),
                    orderItem.getPrice()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(OrderItem orderItem) {
        try {
            jdbcTemplate.update(DELETE_ORDER_ITEM, orderItem.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<OrderItem> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_ORDER_ITEMS, orderItemRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_ORDER_ITEMS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
