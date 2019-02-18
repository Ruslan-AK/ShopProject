package com.Kutugin.services.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.ClientDaoImpl2;
import com.Kutugin.domain.Client;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.validators.ValidationService;

import java.util.List;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientServiceImpl implements ClientService {
    //private ClientDao clientDao = new ClientDaoImpl();
    private ClientDao clientDao = ClientDaoImpl2.getInstance();
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public boolean createClient(String name, String surmame, String phoneNumber) {
        return clientDao.saveClient(new Client(name,surmame,phoneNumber));
    }

    @Override
    public void createClient(String name, String surmame, int age, String phoneNumber, String email) {
//        clientDao.saveClient(new Client(name,surmame,age,phoneNumber,email));
        try{
            validationService.validateAge(age);
            Client client = new Client(name,surmame,age,phoneNumber,email);
            boolean result = clientDao.saveClient(client);
        } catch (BusinessException ex){
            ex.printStackTrace();
        }
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

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
