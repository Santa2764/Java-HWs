package org.example.dao.clientDAO;

import org.example.dao.CRUDInterface;
import org.example.enums.AggregationType;
import org.example.model.Client;

import java.util.List;

public interface ClientDao extends CRUDInterface<Client> {
    Client getClientById(int id);
    float getClientDiscount(AggregationType type);
    Client getClientByBirthday(AggregationType type);
    List<Client> getClientsByTodayBirthday();
    List<Client> getClientsWithoutEmail();
}
