package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class ClientEMDao implements ClientDao {

    private EntityManager entityManager;

    @Autowired
    public ClientEMDao(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public boolean saveClient(Client client) {
        try {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            System.out.println("Save: " + client);
            entityManager.persist(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("error saveClient");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateClient(long id, Client client) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Client clientDB = entityManager.find(Client.class, id);
        if (client.getEmail() != null) clientDB.setEmail(client.getEmail());
        if (client.getPhoneNumber() != null) clientDB.setPhoneNumber(client.getPhoneNumber());
        if (client.getSurname() != null) clientDB.setSurname(client.getSurname());
        if (client.getName() != null) clientDB.setName(client.getName());
        if (client.getAge() != -1) clientDB.setAge(client.getAge());
        entityManager.getTransaction().commit();
    }

    @Override
    public long getIDByPhoneNumber(String phoneNumber) {
        long id = -1;
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        Query query = entityManager.createQuery("SELECT c FROM Client c WHERE c.phoneNumber=:phone");
        query.setParameter("phone", phoneNumber);
        try {
            id = ((Client) query.getSingleResult()).getId();
        } catch (Exception e) {
            System.out.println("Error getIDByPhoneNumber");
        }
        return id;
    }

    @Override
    public List<Client> getAllClients() {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        List<Client> resultList = entityManager.createQuery("Select t from Client t").getResultList();//hql
        return resultList;
    }

    @Override
    public void deleteClient(long id) {
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
        entityManager.remove(getClientByID(id));
        entityManager.getTransaction().commit();
    }

    @Override
    public boolean isPresent(String phone) {
        Query query = entityManager.createQuery("SELECT 1 FROM Client c WHERE c.phoneNumber=:phone");
        query.setParameter("phone", phone);
        return !(query.getResultList().isEmpty());

    }

    @Override
    public Client getClientByID(long id) {
        try {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            Client client = entityManager.find(Client.class, id);
            return client;
        } catch (Exception e) {
            return null;
        }
    }
}
