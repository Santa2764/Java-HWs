package org.example.service.busines.order;

import org.example.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersByOrderDate(LocalDate date);
    List<Order> getOrdersByBetweenOrderDate(LocalDate fromDate, LocalDate toDate);
}
