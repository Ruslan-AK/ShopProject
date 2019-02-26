package com.Kutugin.dao;

import java.util.List;

import com.Kutugin.domain.Client;

public interface ClientDao {
    boolean saveClient(Client client);

    Client getById(String id);

    List<Client> getAllClients();

    void deleteClient(String id);
}
