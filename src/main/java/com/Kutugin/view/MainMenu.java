package com.Kutugin.view;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu {
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
            System.out.println("1 - Admin\n2 - Client\n0 - Exit program");
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (input) {
                case "1": {
                    System.out.println("Enter as admin");
                    adminMenu.show();
                    break;
                }
                case "2": {
                    System.out.println("Enter as client");
                    clientMenu.show();
                    break;
                }
                case "0": {
                    System.out.println("Exit program");
                    System.exit(0);
                }
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}