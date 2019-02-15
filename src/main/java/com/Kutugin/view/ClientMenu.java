package com.Kutugin.view;

import com.Kutugin.services.ClientService;
import com.Kutugin.services.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();


    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning){
            System.out.println("1 - View product\n2 - Buy product\n0 - Exit to main menu");
            switch (br.readLine()){
                case "1":
                    System.out.println("View product");
                    break;
                case "2":
                    System.out.println("Buy product");
                    break;
                case "0":
                    System.out.println("Exit to main menu");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}
