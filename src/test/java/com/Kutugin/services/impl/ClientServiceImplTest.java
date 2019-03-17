package com.Kutugin.services.impl;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    private ValidationService validationService = new ValidationServiceImpl();
    @Mock
    private ClientDao clientDao;//= Mockito.mock(ClientDao.class);
    private ClientService clientService;

    @Before
    public void init() {
        clientService = new ClientServiceImpl(clientDao, validationService);
    }

    @After
    public void tearDown() {
        clientService = null;
    }


    @Test
    public void createClientRightParameters() {
        //Given
        String name = "client";
        String surname = "client";
        String age = "30";
        String phone = "0677676767";
        String email = "client@client.com";
        long id = clientDao.getNextByMaxID();
        //When
        Mockito.when(clientDao.saveClient(new Client(id, name, surname, age, email, phone))).thenReturn(true);
        //Then
        boolean res = clientService.createClient(name, surname, age, email, phone);
        Assert.assertTrue(res);
    }

    @Test
    public void createClientWrongParameters() {
        //Given
        String name = "client";
        String surname = "client";
        String age = "30";
        String phone = "06776767678";
        String email = "client@client.com";
        long id = clientDao.getNextByMaxID();
        //When
        Mockito.when(clientDao.saveClient(new Client(id, name, surname, age, email, phone))).thenReturn(true);
        //Then
        boolean res = clientService.createClient(name, surname, age, email, phone);
        Assert.assertFalse(res);
    }

    @Test
    public void deleteClientTest() {
        long id = 1;
        clientService.deleteClient(id);
        verify(clientDao).deleteClient(id);
    }

    @Test
    public void updateClientTest() {
        long idC = 1;
        String name = "client";
        String surname = "client";
        String age = "30";
        String phone = "06776767678";
        String email = "client@client.com";
        long id = clientDao.getNextByMaxID();
        Client client = new Client(id, name, surname, age, email, phone);
        clientService.updateClient(idC, client);
        verify(clientDao).updateClient(idC, client);
    }

    @Test
    public void getIDByPhoneNumberSuccessTest() {
        //Given
        String phone = "0677777777";
        //When
        Mockito.when(clientDao.getIDByPhoneNumber(phone)).thenReturn(anyLong());
        //Then
        long res = clientService.getIDByPhoneNumber(phone);
        Assert.assertEquals(0, res);//-1 means error
    }

    @Test
    public void getIDByPhoneNumberFailTest() {
        //Given
        String phone = "asd23sdf";
        //When
        Long id = 1L;
        Mockito.when(clientDao.getIDByPhoneNumber(phone)).thenReturn(id);
        //Then
        long res = clientService.getIDByPhoneNumber(phone);
        Assert.assertEquals(-1, res);//-1 means error
    }

    @Test
    public void isPresentTest() {
        //Given
        String name = "client";
        String surname = "client";
        String age = "30";
        String phone = "06776767678";
        String email = "client@client.com";
        long id = clientDao.getNextByMaxID();
        Client client = new Client(id, name, surname, age, email, phone);
        //When
        Mockito.when(clientDao.isPresent(client.getPhoneNumber())).thenReturn(true);
        //Then
        boolean res = clientService.isPresent(client.getPhoneNumber());
        Assert.assertTrue(res);
    }

    @Test
    public void getClientByIDTest() {
        //Given
        long id = 1;
        String name = "test";
        String surname = "test";
        String age = "10";
        String phone = "0676387370";
        String email = "test@test.com";
        Client expectedClient = new Client(id, name, surname, age, email, phone);
        //When
        Mockito.when(clientDao.getClientByID(id)).thenReturn(expectedClient);
        Client client = clientService.getClientByID(id);
        //Then
        Mockito.verify(clientDao).getClientByID(id);//was called with id?
        Mockito.verify(clientDao, Mockito.times(1)).getClientByID(id);//called once
        Mockito.verifyNoMoreInteractions(clientDao);//nothing else calling
        Assert.assertEquals(expectedClient, client);
    }
}