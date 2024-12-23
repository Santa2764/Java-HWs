package org.example.dao.assortementDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Assortement;
import org.example.model.Personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssortementDaoImpl implements AssortementDao {
    private static final String FIND_ALL_ASSORTEMENTS = "SELECT * FROM assortement";
    private static final String SAVE_ASSORTEMENT = """
        INSERT INTO assortement(
            nameEn, nameRu, price, idType
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_ASSORTEMENT = """
            UPDATE assortement SET
                       nameEn = ?,
                       nameRu = ?,
                       price = ?,
                       idType = ? 
            WHERE assortement.id = ?
            """;
    private static final String DELETE_ALL_ASSORTEMENTS = "DELETE FROM assortement";
    private static final String DELETE_ASSORTEMENT = "DELETE FROM assortement WHERE assortement.id = ?";


    @Override
    public List<Assortement> getAssortementsByType(int idType) {
        List<Assortement> assortements = findAll();
        return assortements.stream()
                .filter(a -> a.getIdTypeAssortement() == idType)
                .toList();
    }

    @Override
    public Assortement getAssortementById(int id) {
        List<Assortement> assortements = findAll();
        return assortements.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void save(Assortement assortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ASSORTEMENT)) {
            ps.setString(1, assortement.getNameEn());
            ps.setString(2, assortement.getNameRu());
            ps.setDouble(3, assortement.getPrice());
            ps.setInt(4, assortement.getIdTypeAssortement());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Assortement> assortements) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_ASSORTEMENT)) {
            for (var currAssortement : assortements) {
                ps.setString(1, currAssortement.getNameEn());
                ps.setString(2, currAssortement.getNameRu());
                ps.setDouble(3, currAssortement.getPrice());
                ps.setInt(4, currAssortement.getIdTypeAssortement());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Assortement assortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ASSORTEMENT)) {
            ps.setString(1, assortement.getNameEn());
            ps.setString(2, assortement.getNameRu());
            ps.setDouble(3, assortement.getPrice());
            ps.setInt(4, assortement.getIdTypeAssortement());
            ps.setInt(5, assortement.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Assortement assortement) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ASSORTEMENT)) {
            ps.setInt(1, assortement.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Assortement> findAll() {
        List<Assortement> resultAddAssortements = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_ASSORTEMENTS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Assortement addAssortement = new Assortement();
                addAssortement.setId(result.getInt(1));
                addAssortement.setNameEn(result.getString(2));
                addAssortement.setNameRu(result.getString(3));
                addAssortement.setPrice(result.getDouble(4));
                addAssortement.setIdTypeAssortement(result.getInt(5));
                resultAddAssortements.add(addAssortement);
            }
            return resultAddAssortements;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddAssortements;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_ASSORTEMENTS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
