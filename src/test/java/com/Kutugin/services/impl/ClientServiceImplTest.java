//package com.Kutugin.services.impl;
//
//import com.Kutugin.dao.ClientDao;
//import com.Kutugin.domain.Client;
//import com.Kutugin.services.ClientService;
//import com.Kutugin.validators.ValidationService;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ClientServiceImplTest {
//
//    private ValidationService validationService = Mockito.mock(ValidationService.class);
//    //new ValidationServiceImpl();
//    @Mock
//    private ClientDao clientDao;//= Mockito.mock(ClientDao.class);
//    private ClientService clientService;
//
//    @Before
//    public void init() {
//        clientService = new ClientServiceImpl(clientDao, validationService);
//    }
//
//    @After
//    public void tearDown() {
//        clientService = null;
//    }
//
//
//    @Test
//    public void createClientNotExistsInDB() {
//        //Given
//        String name = "client";
//        String surname = "client";
//        String age = "23";
//        String phone = "06776767";
//        String email = "client@client.com";
//        //When
//        Mockito.when(clientService.createClient(name, surname, age, email, phone)).thenReturn();
//        //Then
//
//        System.out.println("Test");
//    }
//
//    @Test
//    public void findClientTest() {
//        //Given
//        String id = "1";
//        String name = "test";
//        String surname = "test";
//        String age = "10";
//        String phone = "0676387370";
//        String email = "test@test.com";
//        Client expectedClient = new Client(name, surname, age, email, phone);
//        Mockito.when(clientDao.getByPhoneNumber(id)).thenReturn(expectedClient);//modulate servise
//        Mockito.when(clientDao.getByPhoneNumber("0")).thenReturn(expectedClient);//modulate servise
//        //When
//        Client client = clientService.getByPhoneNumber(id);
//        //Then
//        Mockito.verify(clientDao).getByPhoneNumber("1");//вызывался ли с данным параметром
//        Mockito.verify(clientDao, Mockito.times(1)).getByPhoneNumber("1");//вызывался 1 раз?
//        Mockito.verifyNoMoreInteractions(clientDao);//больше ничего не вызывалось?
//        Assert.assertEquals(expectedClient, client);
//    }
//}