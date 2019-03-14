package com.Kutugin.dao;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientDao {
    void saveClient(Client client);

    void updateClient(long id, Client client);

    long getIDByPhoneNumber(String phoneNumber);

    List<Client> getAllClients();

    void deleteClient(long id);

    boolean isInDB(String phone);

    long getNextByMaxID();

    Client getClientByID(long id);
}
