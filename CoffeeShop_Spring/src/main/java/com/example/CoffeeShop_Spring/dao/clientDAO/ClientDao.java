package com.example.CoffeeShop_Spring.dao.clientDAO;

import com.example.CoffeeShop_Spring.dao.CRUDInterface;
import com.example.CoffeeShop_Spring.enums.AggregationType;
import com.example.CoffeeShop_Spring.model.Client;

import java.util.List;

public interface ClientDao extends CRUDInterface<Client> {
    Client getClientById(int id);
    float getClientDiscount(AggregationType type);
    Client getClientByBirthday(AggregationType type);
    List<Client> getClientsByTodayBirthday();
    List<Client> getClientsWithoutEmail();
}
