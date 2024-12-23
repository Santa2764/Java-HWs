package com.example.CoffeeShop_Spring.dao.clientDAO;

import com.example.CoffeeShop_Spring.AppStarter;
import com.example.CoffeeShop_Spring.enums.AggregationType;
import com.example.CoffeeShop_Spring.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ClientRowMapper clientRowMapper;


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

        try {
            discount = jdbcTemplate.queryForObject(query, Float.class);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return discount;
    }

    @Override
    public Client getClientByBirthday(AggregationType type) {
        Client client = null;
        String query = String.format(GET_BY_BIRTHDAY, type.getSqlFunction());

        try {
            client = jdbcTemplate.queryForObject(query, clientRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return client;
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
        try {
            jdbcTemplate.update(SAVE_CLIENT,
                    client.getName(),
                    client.getSurname(),
                    client.getPatronymic(),
                    java.sql.Date.valueOf(client.getBirthdate()),
                    client.getNumTel(),
                    client.getEmail(),
                    client.getDiscount()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public int[] saveMany(List<Client> clients) {
        return jdbcTemplate.batchUpdate(SAVE_CLIENT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Client client = clients.get(i);
                        ps.setString(1, client.getName());
                        ps.setString(2, client.getSurname());
                        ps.setString(3, client.getPatronymic());
                        ps.setDate(4, java.sql.Date.valueOf(client.getBirthdate()));
                        ps.setString(5, client.getNumTel());
                        ps.setString(6, client.getEmail());
                        ps.setInt(7, client.getDiscount());
                    }
                    @Override
                    public int getBatchSize() {
                        return clients.size();
                    }
                }
        );
    }

    @Override
    public void update(Client client) {
        try {
            jdbcTemplate.update(UPDATE_CLIENT,
                    client.getName(),
                    client.getSurname(),
                    client.getPatronymic(),
                    java.sql.Date.valueOf(client.getBirthdate()),
                    client.getNumTel(),
                    client.getEmail(),
                    client.getDiscount()
            );
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    public void delete(Client client) {
        try {
            jdbcTemplate.update(DELETE_CLIENT, client.getId());
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Client> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_CLIENTS, clientRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteAll() {
        try {
            jdbcTemplate.update(DELETE_ALL_CLIENTS);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }
    }


    private List<Client> getClientsByQuery(String query) {
        List<Client> clients = null;

        try {
            clients = jdbcTemplate.query(query, clientRowMapper);
        } catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return clients;
    }
}
