package com.Kutugin.services;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientService {
    void createClient(String name, String surmame, String age, String email, String phoneNumber);//for Admin

    void deleteClient(Client client);

    boolean contains(String id);

    void updateClient(Client client, int paramNumber, String param);

    Client getById(String id);

    List<Client> getAllClients();
}
