package com.example.CoffeeShop_Spring.service.busines.orderItems;

import com.example.CoffeeShop_Spring.dao.orderItemDAO.OrderItemDao;
import com.example.CoffeeShop_Spring.model.Assortement;
import com.example.CoffeeShop_Spring.model.Order;
import com.example.CoffeeShop_Spring.model.OrderItem;
import com.example.CoffeeShop_Spring.model.TypeAssortement;
import com.example.CoffeeShop_Spring.service.busines.assortement.AssortementService;
import com.example.CoffeeShop_Spring.service.busines.typeAssortement.TypeAssortementService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Data
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private TypeAssortementService typeAssortementService;

    @Autowired
    private AssortementService assortementService;

    @Autowired
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
        return orderItems.stream()
                .filter(item -> {
                    Assortement assortement = assortementService.getAssortementById(item.getIdAssortement());
                    TypeAssortement type = typeAssortementService.getTypeAssortementById(assortement.getIdTypeAssortement());
                    return type.getName().equalsIgnoreCase(findType);
                })
                .toList();
    }
}
