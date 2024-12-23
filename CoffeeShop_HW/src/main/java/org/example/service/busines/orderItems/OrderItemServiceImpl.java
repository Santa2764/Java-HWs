package org.example.service.busines.orderItems;

import lombok.*;
import org.example.dao.assortementDAO.AssortementDaoImpl;
import org.example.dao.orderItemDAO.OrderItemDao;
import org.example.dao.typeAssortementDAO.TypeAssortementDaoImpl;
import org.example.model.Assortement;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.TypeAssortement;
import org.example.service.busines.assortement.AssortementService;
import org.example.service.busines.assortement.AssortementServiceImpl;
import org.example.service.busines.typeAssortement.TypeAssortementService;
import org.example.service.busines.typeAssortement.TypeAssortementServiceImpl;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemDao.findAll();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrdersId(List<Order> orders) {
        List<OrderItem> orderItems = getAllOrderItems();
        List<Integer> orderIds = orders.stream().map(Order::getId).toList();
        return orderItems.stream()
                .filter(oi -> orderIds.contains(oi.getOrderId()))
                .toList();
    }

    @Override
    public List<OrderItem> getOrderItemsByTypeAssortement(List<OrderItem> orderItems, String findType) {
        TypeAssortementService typeAssortementService = new TypeAssortementServiceImpl(new TypeAssortementDaoImpl());
        AssortementService assortementService = new AssortementServiceImpl(new AssortementDaoImpl());

        return orderItems.stream()
                .filter(item -> {
                    Assortement assortement = assortementService.getAssortementById(item.getIdAssortement());
                    TypeAssortement type = typeAssortementService.getTypeAssortementById(assortement.getIdTypeAssortement());
                    return type.getName().equalsIgnoreCase(findType);
                })
                .toList();
    }
}
