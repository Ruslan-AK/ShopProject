package com.Kutugin.dao;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientDao {
    void saveClient(Client client);

    Client getById(String id);

    List<Client> getAllClients();

    void deleteClient(String id);

    boolean contains(String id);
}
