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
    public boolean createClient(String name, String surname, String age, String email, String phoneNumber) {
        try {
            validationService.validateEmail(email);
            validationService.validateName(surname);
            validationService.validateName(name);
            validationService.validateAge(age);
            validationService.validatePhoneNumber(phoneNumber);
            long id = getNextByMaxID();
            Client client = new Client(id, name, surname, age, email, phoneNumber);
            return clientDao.saveClient(client);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public void deleteClient(long id) {
        clientDao.deleteClient(id);
    }

    @Override
    public boolean isPresent(String phone) {
        return clientDao.isPresent(phone);
    }

    @Override
    public void updateClient(long currentClientID, Client client) {
        clientDao.updateClient(currentClientID, client);
    }

    @Override
    public long getIDByPhoneNumber(String phoneNumber) {
        try {
            validationService.validatePhoneNumber(phoneNumber);
            return clientDao.getIDByPhoneNumber(phoneNumber);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public Client getClientByID(long id) {
        return clientDao.getClientByID(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    private long getNextByMaxID() {
        return clientDao.getNextByMaxID();
    }
}
