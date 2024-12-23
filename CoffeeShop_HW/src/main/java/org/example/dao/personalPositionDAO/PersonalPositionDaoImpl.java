package org.example.dao.personalPositionDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.PersonalPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalPositionDaoImpl implements PersonalPositionDao {
    private static final String FIND_ALL_TYPE_POSITIONS = "SELECT * FROM personalposition";
    private static final String SAVE_TYPE_POSITION = """
        INSERT INTO personalposition(
            name
        )
            VALUES(?)
        """;
    private static final String UPDATE_TYPE_POSITION = """
            UPDATE personalposition SET
                       name = ?
            WHERE personalposition.id = ?
            """;
    private static final String DELETE_ALL_TYPE_POSITIONS = "DELETE FROM personalposition";
    private static final String DELETE_TYPE_POSITION = "DELETE FROM personalposition WHERE personalposition.id = ?";

    @Override
    public void save(PersonalPosition position) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_TYPE_POSITION)) {
            ps.setString(1, position.getName());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<PersonalPosition> positions) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_TYPE_POSITION)) {
            for (var type : positions) {
                ps.setString(1, type.getName());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(PersonalPosition position) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_TYPE_POSITION)) {
            ps.setString(1, position.getName());
            ps.setInt(2, position.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(PersonalPosition position) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_TYPE_POSITION)) {
            ps.setInt(1, position.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<PersonalPosition> findAll() {
        List<PersonalPosition> resultAddPositions = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_TYPE_POSITIONS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                PersonalPosition addPosition = new PersonalPosition();
                addPosition.setId(result.getInt(1));
                addPosition.setName(result.getString(2));
                resultAddPositions.add(addPosition);
            }
            return resultAddPositions;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddPositions;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_TYPE_POSITIONS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
