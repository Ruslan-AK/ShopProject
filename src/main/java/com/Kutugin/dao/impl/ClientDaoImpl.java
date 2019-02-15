package com.Kutugin.dao.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientDaoImpl implements ClientDao{

    private static List<Client> clients = new ArrayList<>();

    @Override
    public boolean saveClient(Client client) {
        if(!clients.contains(client)){
            clients.add(client);
            System.out.println(client+" saved");
            return true;
        }
        System.out.println(client+" already saved");
        return false;
    }

    @Override
    public Client getById(long id){
        for (Client c:clients){
            if(c.getId()==id) return c;
        }
        return null;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public void deleteClient(Client client) {
        clients.remove(client);
    }

    //delete by id

}
