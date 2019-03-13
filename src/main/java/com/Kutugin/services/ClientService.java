package com.Kutugin.services;

import com.Kutugin.domain.Client;

import java.util.List;

public interface ClientService {
    void createClient(String name, String surmame, String age, String email, String phoneNumber);//for Admin

    void deleteClient(long id);

    boolean isInDB(long id);

    void updateClient(long currentClientID, Client client);

    long getIDByPhoneNumber(String id);

    Client getClientByID(long id);

    List<Client> getAllClients();
}
