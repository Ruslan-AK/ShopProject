package com.Kutugin.view;

import com.Kutugin.dao.impl.H2DB.Init;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu implements IMenu {
    private final BufferedReader br;
    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;

    public MainMenu(BufferedReader br, AdminMenu adminMenu, ClientMenu clientMenu) {
        this.br = br;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
    }

    public void showMenu() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("$$$$$$$$$$$ WELCOME TO SHOP $$$$$$$$$$$");
            System.out.println("Select your role:");
            System.out.println("1 - Admin\n2 - Client\n3 - init\n0 - Exit program");
            System.out.println("$$$$$$$$$$$ WELCOME TO SHOP $$$$$$$$$$$");
            String input = getInput();
            switch (input) {
                case "1": {
                    System.out.println("Enter as admin");
                    adminMenu.show();
                    break;
                }
                case "2": {
                    System.out.println("Chose action");
                    clientMenu.show();
                    break;
                }
                case"3":{
                    Init init = new Init();
                    init.initDBs();
                    break;
                }
                case "0": {
                    System.out.println("Program closed\nCome again");
                    System.exit(0);
                }
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    @Override
    public String getInput() {
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Wrong input!");
        }
        return input;
    }
}