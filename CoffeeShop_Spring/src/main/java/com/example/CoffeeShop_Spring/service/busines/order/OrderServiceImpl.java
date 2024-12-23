package com.example.CoffeeShop_Spring.service.busines.order;

import com.example.CoffeeShop_Spring.dao.orderDAO.OrderDao;
import com.example.CoffeeShop_Spring.model.Order;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Data
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> getOrdersByOrderDate(LocalDate date) {
        List<Order> orders = getAllOrders();
        return orders.stream()
                .filter(o -> o.getOrderDate().equals(date))
                .toList();
    }

    @Override
    public List<Order> getOrdersByBetweenOrderDate(LocalDate fromDate, LocalDate toDate) {
        List<Order> orders = getAllOrders();
        return orders.stream()
                .filter(o -> !o.getOrderDate().isBefore(fromDate) && !o.getOrderDate().isAfter(toDate))
                .toList();
    }
}
