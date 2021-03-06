package com.Kutugin.dao;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientDao {
    void saveClient(Client client) throws Exception;

    void updateClient(long id, Client client);

    long getIDByPhoneNumber(String phoneNumber);

    List<Client> getAllClients();

    void deleteClient(long id);

    boolean isPresent(String phone);

    Client getClientByID(long id);
}
