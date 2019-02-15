package com.Kutugin.services;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.ClientDaoImpl;
import com.Kutugin.domain.Client;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();
    @Override
    public void createClient(String name, String surmame, String phoneNumber) {
        clientDao.saveClient(new Client(name,surmame,phoneNumber));
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.deleteClient(client);
    }

    @Override
    public boolean contains(long id) {
        if (clientDao.getById(id)!=null) return true;
        return false;
    }

    @Override
    public void updateClient(Client client, String name, String surmame, String phoneNumber) {
        client.setName(name);
        client.setSurmame(surmame);
        client.setPhoneNumber(phoneNumber);
        System.out.println(client+" updated");
    }

    @Override
    public Client getById(long id) {
        return clientDao.getById(id);
    }
}
