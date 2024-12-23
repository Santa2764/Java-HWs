package org.example.dao.personalDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.Personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalDaoImpl implements PersonalDao {
    private static final String FIND_ALL_PERSONALS = "SELECT * FROM personal";
    private static final String SAVE_PERSONAL = """
        INSERT INTO personal(
            name, surname, patronymic, numTel, email, idPosition
        )
            VALUES(?, ?, ?, ?, ?, ?)
        """;
    private static final String UPDATE_PERSONAL = """
            UPDATE personal SET
                       name = ?,
                       surname = ?,
                       patronymic = ?,
                       numTel = ?,
                       email = ?,
                       idPosition = ?
            WHERE personal.id = ?
            """;
    private static final String DELETE_ALL_PERSONALS = "DELETE FROM personal";
    private static final String DELETE_PERSONAL = "DELETE FROM personal WHERE personal.id = ?";


    @Override
    public List<Personal> getPersonalsByPosition(int idPosition) {
        List<Personal> personals = findAll();
        return personals.stream()
                .filter(p -> p.getIdPosition() == idPosition)
                .toList();
    }

    @Override
    public Personal getPersonalById(int id) {
        List<Personal> personals = findAll();
        return personals.stream()
                .filter(personal -> personal.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public void save(Personal personal) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONAL)) {
            ps.setString(1, personal.getName());
            ps.setString(2, personal.getSurname());
            ps.setString(3, personal.getPatronymic());
            ps.setString(4, personal.getNumTel());
            ps.setString(5, personal.getEmail());
            ps.setInt(6, personal.getIdPosition());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Personal> personals) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONAL)) {
            for (var currPersonal : personals) {
                ps.setString(1, currPersonal.getName());
                ps.setString(2, currPersonal.getSurname());
                ps.setString(3, currPersonal.getPatronymic());
                ps.setString(4, currPersonal.getNumTel());
                ps.setString(5, currPersonal.getEmail());
                ps.setInt(6, currPersonal.getIdPosition());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Personal personal) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PERSONAL)) {
            ps.setString(1, personal.getName());
            ps.setString(2, personal.getSurname());
            ps.setString(3, personal.getPatronymic());
            ps.setString(4, personal.getNumTel());
            ps.setString(5, personal.getEmail());
            ps.setInt(6, personal.getIdPosition());
            ps.setInt(7, personal.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Personal personal) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PERSONAL)) {
            ps.setInt(1, personal.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Personal> findAll() {
        List<Personal> resultAddPersonals = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_PERSONALS);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Personal addPersonal = new Personal();
                addPersonal.setId(result.getInt(1));
                addPersonal.setName(result.getString(2));
                addPersonal.setSurname(result.getString(3));
                addPersonal.setPatronymic(result.getString(4));
                addPersonal.setNumTel(result.getString(5));
                addPersonal.setEmail(result.getString(6));
                addPersonal.setIdPosition(result.getInt(7));
                resultAddPersonals.add(addPersonal);
            }
            return resultAddPersonals;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddPersonals;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_PERSONALS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
