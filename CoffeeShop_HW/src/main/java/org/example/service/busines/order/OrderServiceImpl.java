package org.example.service.busines.order;

import lombok.*;

import org.example.dao.orderDAO.OrderDao;
import org.example.model.Order;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
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
