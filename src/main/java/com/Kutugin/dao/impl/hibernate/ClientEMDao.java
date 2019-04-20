package com.Kutugin.dao.impl.hibernate;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientEMDao implements ClientDao {
    @PersistenceContext(unitName = "factory")
    private EntityManager entityManager;

    @Override
    public void saveClient(Client client) {
            entityManager.persist(client);
    }

    @Override
    public void updateClient(long id, Client client) {
        Client clientDB = entityManager.find(Client.class, id);
        if (client.getEmail() != null) clientDB.setEmail(client.getEmail());
        if (client.getPhoneNumber() != null) clientDB.setPhoneNumber(client.getPhoneNumber());
        if (client.getSurname() != null) clientDB.setSurname(client.getSurname());
        if (client.getName() != null) clientDB.setName(client.getName());
        if (client.getAge() != -1) clientDB.setAge(client.getAge());
    }

    @Override
    public long getIDByPhoneNumber(String phoneNumber) {
        long id = -1;
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
        List<Client> resultList = entityManager.createQuery("Select t from Client t").getResultList();//hql
        return resultList;
    }

    @Override
    public void deleteClient(long id) throws HibernateException {
        entityManager.remove(getClientByID(id));
    }

    @Override
    public boolean isPresent(String phone) {
        Query query = entityManager.createQuery("SELECT 1 FROM Client c WHERE c.phoneNumber=:phone");
        query.setParameter("phone", phone);
        return !(query.getResultList().isEmpty());
    }

    @Override
    public Client getClientByID(long id) {
            Client client = entityManager.find(Client.class, id);
            return client;
    }
}
