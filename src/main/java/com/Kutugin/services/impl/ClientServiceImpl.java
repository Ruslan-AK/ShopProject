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
    public void updateClient(Client client, int paramNumber, String param) {
        switch (paramNumber) {
            case 1: {
                client.setName(param);
                break;
            }
            case 2: {
                client.setSurmame(param);
                break;
            }
            case 3: {
                client.setAge(Integer.valueOf(param));
                break;
            }
            case 4: {
                client.setEmail(param);
                break;
            }
            case 5: {
                client.setPhoneNumber(param);
                break;
            }
            default: {
                System.out.println("Eror update!");
            }
        }
        System.out.println(client + " updated");
    }

    @Override
    public Client getById(String id) {
        return clientDao.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
