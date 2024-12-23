package org.example.dao.typeAssortementDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Personal;
import org.example.model.TypeAssortement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeAssortementDaoImpl implements TypeAssortementDao {
    private static final String FIND_ALL_TYPE_ASSORTEMENTS = "SELECT * FROM typeassortement";
    private static final String SAVE_TYPE_ASSORTEMENT = """
        INSERT INTO typeassortement(
            name
        )
            VALUES(?)
        """;
    private static final String UPDATE_TYPE_ASSORTEMENT = """
            UPDATE typeassortement SET
                       name = ?
            WHERE typeassortement.id = ?
            """;
    private static final String DELETE_ALL_TYPE_ASSORTEMENTS = "DELETE FROM typeassortement";
    private static final String DELETE_TYPE_ASSORTEMENT = "DELETE FROM typeassortement WHERE typeassortement.id = ?";

    @Override
    public void save(TypeAssortement typeAssortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_TYPE_ASSORTEMENT)) {
            ps.setString(1, typeAssortement.getName());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<TypeAssortement> typeAssortements) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_TYPE_ASSORTEMENT)) {
            for (var type : typeAssortements) {
                ps.setString(1, type.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(TypeAssortement typeAssortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_TYPE_ASSORTEMENT)) {
            ps.setString(1, typeAssortement.getName());
            ps.setInt(2, typeAssortement.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(TypeAssortement typeAssortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_TYPE_ASSORTEMENT)) {
            ps.setInt(1, typeAssortement.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<TypeAssortement> findAll() {
        List<TypeAssortement> resultAddTypes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_TYPE_ASSORTEMENTS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                TypeAssortement addType = new TypeAssortement();
                addType.setId(result.getInt(1));
                addType.setName(result.getString(2));
                resultAddTypes.add(addType);
            }
            return resultAddTypes;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddTypes;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_TYPE_ASSORTEMENTS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
