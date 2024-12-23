package org.example.service.busines.orderItems;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.TypeAssortement;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrdersId(List<Order> orders);
    List<OrderItem> getOrderItemsByTypeAssortement(List<OrderItem> orderItems, String findType);
}
