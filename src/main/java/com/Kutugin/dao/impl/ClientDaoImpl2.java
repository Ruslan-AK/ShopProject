package com.Kutugin.dao.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dp-ptcstd-49 on 15.02.2019.
 */
public class ClientDaoImpl2 implements ClientDao{

    private Map<Long,Client> map = new HashMap<>();
    private static ClientDao clientDao = new ClientDaoImpl2();
    private ClientDaoImpl2() {

    }

    public static ClientDao getInstance(){
        return clientDao;
    }

    @Override
    public boolean saveClient(Client client) {
        map.put(client.getId(),client);
        System.out.println("Saving client");
        return true;
    }

    @Override
    public Client getById(long id) {
        return map.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteClient(Client client) {

    }
}
