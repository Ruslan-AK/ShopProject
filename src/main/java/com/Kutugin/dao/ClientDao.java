package com.Kutugin.dao;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientDao {
    void saveClient(Client client);

    void updateClient(String phoneNumber, int paramNumber,String param);

    Client getByPhoneNumber(String phoneNumber);

    List<Client> getAllClients();

    void deleteClient(String id);

    boolean contains(String id);
}
