package com.Kutugin.services;

import com.Kutugin.domain.Client;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public interface ClientService {

    void createClient(String name, String surmame, String phoneNumber);
    void deleteClient(Client client);
    boolean contains(long id);
    void updateClient(Client client,String name, String surmame, String phoneNumber);
    Client getById(long id);

}
