package com.example.CoffeeShop_Spring.dao.orderItemDAO;

import com.example.CoffeeShop_Spring.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getInt("id"));
        orderItem.setOrderId(rs.getInt("idOrder"));
        orderItem.setIdAssortement(rs.getInt("idAssortement"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getDouble("price"));
        return orderItem;
    }
}
