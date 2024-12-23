package com.example.CoffeeShop_Spring.service.busines.order;

import com.example.CoffeeShop_Spring.model.Order;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersByOrderDate(LocalDate date);
    List<Order> getOrdersByBetweenOrderDate(LocalDate fromDate, LocalDate toDate);
}
