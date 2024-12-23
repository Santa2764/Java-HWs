package com.example.CoffeeShop_Spring.service.busines.client;

import com.example.CoffeeShop_Spring.enums.AggregationType;
import com.example.CoffeeShop_Spring.model.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);
    List<Client> getAllClients();
    Client getClientById(int id);
    void updateClientDiscount(Client client, int discount);
    void updateClient(Client client);
    boolean deleteClientById(int id);
    float getClientDiscount(AggregationType type);
    List<Client> getClientsByDiscount(int discount);
    Client getClientByBirthday(AggregationType type);
    List<Client> getClientsByTodayBirthday();
    List<Client> getClientsWithoutEmail();
}
