package com.Kutugin.dao.impl.noDB;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private Map<String, Client> map = new HashMap<>();
    private static ClientDao clientDao = new ClientDaoImpl();//for Singleton
    private ClientDB db = new ClientDB();

    private ClientDaoImpl() {
        for (Client c : db.getClients()) {
            map.put(c.getPhoneNumber(), c);
        }
    }

    public static ClientDao getInstance() {
        return clientDao;
    }

    @Override
    public void saveClient(Client client) {
        map.put(client.getPhoneNumber(), client);
    }

    @Override
    public Client getById(String id) {
        return map.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteClient(String id) {
        map.remove(id);
    }

    @Override
    public boolean contains(String id) {
        return map.containsKey(id);
    }
}