package com.Kutugin.view;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class MainMenu {
    private final BufferedReader br;
    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;

    public MainMenu(BufferedReader br, AdminMenu adminMenu, ClientMenu clientMenu) {
        this.br = br;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("$$$$$$$$$$$ WELCOME TO SHOP $$$$$$$$$$$");
            System.out.println("Select your role:");
            System.out.println("1 - Admin\n2 - Client\n0 - Exit program");
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