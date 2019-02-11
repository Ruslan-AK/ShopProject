package com.Kutugin.services;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDao() {
        @Override
        public boolean saveClient(Client client) {
            return true;
        }
    };
    @Override
    public void createClient(String name, String surmame, String phoneNumber) {
        clientDao.saveClient(new Client(name,surmame,phoneNumber));
        Client client = new Client(name,surmame,phoneNumber);
        boolean result = clientDao.saveClient(client);
        if(result){
            System.out.println("Client saved"+client);
        }
    }

    @Override
    public void deleteClient() {

    }
}
