package com.Kutugin.dao.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientDaoImpl implements ClientDao{
    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving Client Dao");
        return true;
    }
}
