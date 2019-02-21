package com.Kutugin.services;

import com.Kutugin.domain.Client;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public interface ClientService {

    void createClient(String name, String surmame, String phoneNumber);//for Client
    void createClient(String name, String surmame, String age, String phoneNumber, String email);//for Admin
    void deleteClient(Client client);
    boolean contains(long id);
    void updateClient(Client client,String name, String surmame, String phoneNumber);
    Client getById(long id);
    List<Client> getAllClients();

}
