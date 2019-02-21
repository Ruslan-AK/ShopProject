package com.Kutugin;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.ClientDaoImpl;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.services.impl.ClientServiceImpl;
import com.Kutugin.services.impl.ProductServiceImpl;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;
import com.Kutugin.view.AdminMenu;
import com.Kutugin.view.ClientAuthentication;
import com.Kutugin.view.ClientMenu;
import com.Kutugin.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        ClientDao clientDao = ClientDaoImpl.getInstance();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductServise productService = new ProductServiceImpl();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService, validationService);
        ClientAuthentication clientAuthentication = new ClientAuthentication(br, clientService, validationService);
        ClientMenu clientMenu = new ClientMenu(productService, br, clientService, clientAuthentication, validationService);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}