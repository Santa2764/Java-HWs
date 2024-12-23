package com.example.CoffeeShop_Spring.dao.orderDAO;

import com.example.CoffeeShop_Spring.model.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setOrderDate(rs.getDate("orderDate").toLocalDate());
        order.setIdClient(rs.getInt("idClient"));
        return order;
    }
}
