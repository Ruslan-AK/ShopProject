package com.Kutugin.dao.impl.H2DB;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientEMDao implements ClientDao {
    public ClientEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    private EntityManager entityManager;

    @Override
    public boolean saveClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public void updateClient(long id, Client client) {

    }

    @Override
    public long getIDByPhoneNumber(String phoneNumber) {
        return 0;
    }

    @Override
    public List<Client> getAllClients() {
        entityManager.getTransaction().begin();
        List<Client> resultList = entityManager.createQuery("from Client", Client.class).getResultList();//hql
        return resultList;
    }

    @Override
    public void deleteClient(long id) {

    }

    @Override
    public boolean isPresent(String phone) {
        return false;
    }

    @Override
    public long getNextByMaxID() {
        return 0;
    }

    @Override
    public Client getClientByID(long id) {
        return null;
    }
}
