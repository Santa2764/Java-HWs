package org.example.dao.orderDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String FIND_ALL_ORDERS = "SELECT * FROM \"Order\"";
    private static final String SAVE_ORDER = """
        INSERT INTO \"Order\"(
            orderDate, idClient
        )
            VALUES(?, ?)
        """;
    private static final String UPDATE_ORDER = """
            UPDATE \"Order\" SET
                       orderDate = ?,
                       idClient = ?,
            WHERE \"Order\".id = ?
            """;
    private static final String DELETE_ALL_ORDERS = "DELETE FROM \"Order\"";
    private static final String DELETE_ORDER = "DELETE FROM \"Order\" WHERE \"Order\".id = ?";

    @Override
    public void save(Order order) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER)) {
            ps.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
            ps.setInt(2, order.getIdClient());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Order> orders) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ORDER)) {
            for (var currOrder : orders) {
                ps.setDate(1, java.sql.Date.valueOf(currOrder.getOrderDate()));
                ps.setInt(2, currOrder.getIdClient());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Order order) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ORDER)) {
            ps.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
            ps.setInt(2, order.getIdClient());
            ps.setInt(3, order.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Order order) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ORDER)) {
            ps.setInt(1, order.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> resultAddOrders = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_ORDERS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Order addOrder = new Order();
                addOrder.setId(result.getInt(1));
                addOrder.setOrderDate(result.getDate(2).toLocalDate());
                addOrder.setIdClient(result.getInt(3));
                resultAddOrders.add(addOrder);
            }
            return resultAddOrders;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddOrders;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_ORDERS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
