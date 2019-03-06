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
    public void createClient(String name, String surname, String age, String email, String phoneNumber) {
        try {
            validationService.validateAge(age);
            validationService.validatePhoneNumber(phoneNumber);
            Client client = new Client(name, surname, age, email, phoneNumber);
            clientDao.saveClient(client);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteClient(Client client) {
        clientDao.deleteClient(client.getPhoneNumber());
    }

    @Override
    public boolean contains(String id) {
        return clientDao.contains(id);
    }

    @Override
    public void updateClient(String phoneNumber, int paramNumber, String param) {
        clientDao.updateClient(phoneNumber,paramNumber,param);
    }

    @Override
    public Client getByPhoneNumber(String phoneNumber) {
        return clientDao.getByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
