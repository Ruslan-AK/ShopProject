package com.Kutugin;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.ClientDaoImpl2;
import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ClientServiceImpl;
import com.Kutugin.view.AdminMenu;
import com.Kutugin.view.ClientAuthentification;
import com.Kutugin.view.ClientMenu;
import com.Kutugin.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClientDao clientDao = ClientDaoImpl2.getInstance();
        ClientService clientService = new ClientServiceImpl(clientDao);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService);
        ClientAuthentification clientAuthentification = new ClientAuthentification(br,clientService);
        ClientMenu clientMenu = new ClientMenu(br,clientService);
        MainMenu menu = new MainMenu(br,adminMenu,clientMenu);
        menu.showMenu();
    }
}
