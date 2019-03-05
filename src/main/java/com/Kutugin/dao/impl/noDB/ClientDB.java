package com.Kutugin.dao.impl.noDB;

import com.Kutugin.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDB {

    private List<Client> clients = new ArrayList<>();

    public ClientDB() {
        fillDB();
    }

    private void fillDB() {
        clients.add(new Client("Ruslan", "Kutugin", "26", "kutuginr@gmail.com", "0676387370"));
    }

    public List<Client> getClients() {
        return clients;
    }
}
