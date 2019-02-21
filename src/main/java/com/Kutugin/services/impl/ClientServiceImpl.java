package com.Kutugin.services.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surmame, String age, String email, String phoneNumber) {
        try {
            validationService.validateAge(age);
            validationService.validatePhoneNumber(phoneNumber);
            Client client = new Client(name, surmame, age, email, phoneNumber);
            //for modif
            boolean result = clientDao.saveClient(client);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.deleteClient(client.getId());
    }

    @Override
    public boolean contains(long id) {
        if (clientDao.getById(id) != null) return true;
        return false;
    }

    @Override
    public void updateClient(Client client, String name, String surmame, String phoneNumber) {
        client.setName(name);
        client.setSurmame(surmame);
        client.setPhoneNumber(phoneNumber);
        System.out.println(client + " updated");
    }

    @Override
    public Client getById(long id) {
        return clientDao.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
