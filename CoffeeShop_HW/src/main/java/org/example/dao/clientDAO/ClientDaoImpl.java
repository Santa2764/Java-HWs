package org.example.dao.clientDAO;

import org.example.dao.ConnectionFactory;
import org.example.enums.AggregationType;
import org.example.exception.ConnectionDBException;
import org.example.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL_CLIENTS = "SELECT * FROM client";
    private static final String SAVE_CLIENT = """
        INSERT INTO client(
            name, surname, patronymic, birthdate, numTel, email, discount
        )
            VALUES(?, ?, ?, ?, ?, ?, ?)
        """;
    private static final String UPDATE_CLIENT = """
            UPDATE client SET
                       name = ?,
                       surname = ?,
                       patronymic = ?,
                       birthdate = ?,
                       numTel = ?,
                       email = ?,
                       discount = ?
            WHERE client.id = ?
            """;
    private static final String DELETE_ALL_CLIENTS = "DELETE FROM client";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE client.id = ?";
    private static final String GET_DISCOUNT = "SELECT %s(discount) FROM client";
    private static final String GET_BY_BIRTHDAY = "SELECT * FROM client WHERE birthdate = (SELECT %s(birthdate) FROM client)";
    private static final String GET_BY_BIRTHDAY_TODAY = """
            SELECT * FROM client
            WHERE
                EXTRACT(DAY FROM birthdate) = EXTRACT(DAY FROM CURRENT_DATE) AND
                EXTRACT(MONTH FROM birthdate) = EXTRACT(MONTH FROM CURRENT_DATE)
            """;
    private static final String GET_EMAIL_NULL = "SELECT * FROM client WHERE email IS NULL OR email = ''";


    @Override
    public Client getClientById(int id) {
        List<Client> clients = findAll();
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public float getClientDiscount(AggregationType type) {
        float discount = 0;
        String query = String.format(GET_DISCOUNT, type.getSqlFunction());

        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet result = ps.executeQuery()) {
            if (result.next()) {
                discount = result.getFloat(1);
            }
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
        return discount;
    }

    @Override
    public Client getClientByBirthday(AggregationType type) {
        String query = String.format(GET_BY_BIRTHDAY, type.getSqlFunction());

        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet result = ps.executeQuery()) {
            if (result.next()) {
                Client client = new Client();
                client.setId(result.getInt(1));
                client.setName(result.getString(2));
                client.setSurname(result.getString(3));
                client.setPatronymic(result.getString(4));
                client.setBirthdate(result.getDate(5).toLocalDate());
                client.setNumTel(result.getString(6));
                client.setEmail(result.getString(7));
                client.setDiscount(result.getInt(8));
                return client;
            }
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }

        return null;
    }

    @Override
    public List<Client> getClientsByTodayBirthday() {
        return getClientsByQuery(GET_BY_BIRTHDAY_TODAY);
    }

    @Override
    public List<Client> getClientsWithoutEmail() {
        return getClientsByQuery(GET_EMAIL_NULL);
    }


    @Override
    public void save(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getPatronymic());
            ps.setDate(4, java.sql.Date.valueOf(client.getBirthdate()));
            ps.setString(5, client.getNumTel());
            ps.setString(6, client.getEmail());
            ps.setInt(7, client.getDiscount());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<Client> clients) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_CLIENT)) {
            for (var currClient : clients) {
                ps.setString(1, currClient.getName());
                ps.setString(2, currClient.getSurname());
                ps.setString(3, currClient.getPatronymic());
                ps.setDate(4, java.sql.Date.valueOf(currClient.getBirthdate()));
                ps.setString(5, currClient.getNumTel());
                ps.setString(6, currClient.getEmail());
                ps.setInt(7, currClient.getDiscount());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_CLIENT)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getPatronymic());
            ps.setDate(4, java.sql.Date.valueOf(client.getBirthdate()));
            ps.setString(5, client.getNumTel());
            ps.setString(6, client.getEmail());
            ps.setInt(7, client.getDiscount());
            ps.setInt(8, client.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Client client) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_CLIENT)) {
            ps.setInt(1, client.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Client> findAll() {
        return getClientsByQuery(FIND_ALL_CLIENTS);
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_CLIENTS)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    private List<Client> getClientsByQuery(String query) {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet result = ps.executeQuery()) {
            while (result.next()) {
                Client client = new Client();
                client.setId(result.getInt(1));
                client.setName(result.getString(2));
                client.setSurname(result.getString(3));
                client.setPatronymic(result.getString(4));
                client.setBirthdate(result.getDate(5).toLocalDate());
                client.setNumTel(result.getString(6));
                client.setEmail(result.getString(7));
                client.setDiscount(result.getInt(8));
                clients.add(client);
            }
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return clients;
    }
}
