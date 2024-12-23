package org.example.dao.orderItemDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    private static final String FIND_ALL_ORDER_ITEMS = "SELECT * FROM orderitem";
    private static final String SAVE_ORDER_ITEM = """
        INSERT INTO orderitem(
            idOrder, idAssortement, quantity, price
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_ORDER_ITEM = """
            UPDATE orderitem SET
                       idOrder = ?,
                       idAssortement = ?,
                       quantity = ?,
                       price = ?
            WHERE orderitem.id = ?
            """;
    private static final String DELETE_ALL_ORDER_ITEMS = "DELETE FROM orderitem";
    private static final String DELETE_ORDER_ITEM = "DELETE FROM orderitem WHERE orderitem.id = ?";

    @Override
    public void save(OrderItem orderItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_ITEM)) {
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getIdAssortement());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getPrice());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<OrderItem> orderItems) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER_ITEM)) {
            for (var currOrderItem : orderItems) {
                ps.setInt(1, currOrderItem.getOrderId());
                ps.setInt(2, currOrderItem.getIdAssortement());
                ps.setInt(3, currOrderItem.getQuantity());
                ps.setDouble(4, currOrderItem.getPrice());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(OrderItem orderItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ORDER_ITEM)) {
            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getIdAssortement());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getPrice());
            ps.setInt(5, orderItem.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(OrderItem orderItem) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER_ITEM)) {
            ps.setInt(1, orderItem.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OrderItem> findAll() {
        List<OrderItem> resultAddOrderItems = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_ORDER_ITEMS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                OrderItem addOrderItem = new OrderItem();
                addOrderItem.setId(result.getInt(1));
                addOrderItem.setOrderId(result.getInt(2));
                addOrderItem.setIdAssortement(result.getInt(3));
                addOrderItem.setQuantity(result.getInt(4));
                addOrderItem.setPrice(result.getDouble(5));
                resultAddOrderItems.add(addOrderItem);
            }
            return resultAddOrderItems;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddOrderItems;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_ORDER_ITEMS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
