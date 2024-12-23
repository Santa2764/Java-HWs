package com.example.CoffeeShop_Spring.dao.clientDAO;

import com.example.CoffeeShop_Spring.model.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setName(rs.getString("name"));
        client.setSurname(rs.getString("surname"));
        client.setPatronymic(rs.getString("patronymic"));
        client.setBirthdate(rs.getDate("birthdate").toLocalDate());
        client.setNumTel(rs.getString("numTel"));
        client.setDiscount(rs.getInt("discount"));
        return client;
    }
}
