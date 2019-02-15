package com.Kutugin.dao;

import com.Kutugin.domain.Client;

import java.util.List;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public interface ClientDao {
    boolean saveClient(Client client);
    Client getById(long id);
    List<Client> getClients();
    void deleteClient(Client client);
}
