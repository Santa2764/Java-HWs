package com.example.CoffeeShop_Spring.service.busines.orderItems;

import com.example.CoffeeShop_Spring.model.Order;
import com.example.CoffeeShop_Spring.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrdersId(List<Order> orders);
    List<OrderItem> getOrderItemsByTypeAssortement(List<OrderItem> orderItems, String findType);
}
