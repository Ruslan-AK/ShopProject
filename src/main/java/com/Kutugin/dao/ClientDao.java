package com.Kutugin.dao;

import java.util.List;

import com.Kutugin.domain.Client;

public interface ClientDao {
    void saveClient(Client client);

    Client getById(String id);

    List<Client> getAllClients();

    void deleteClient(String id);

    boolean contains(String id);
}
