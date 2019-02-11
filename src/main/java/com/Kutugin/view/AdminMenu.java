package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class AdminMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();


    public void show() throws IOException {
        getShow();
        boolean isRunning = true;
        while (isRunning){
            switch (br.readLine()){
                case "1":
                    System.out.println("Input name:");
                    String name = br.readLine();
                    System.out.println("Input surname:");
                    String surname = br.readLine();
                    System.out.println("Input phone:");
                    String phoneNumber = br.readLine();
                    clientService.createClient(name,surname, phoneNumber);
                    break;
                case "2":
                    System.out.println("Modify client");
                    break;
                case "3":
                    System.out.println("Remove client");
                    break;
                case "4":
                    System.out.println("Add product");
                    break;
                case "0":
                    System.out.println("Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private void getShow() {
        System.out.println("1 - Add client\n2 - Modify client\n3 - Remove client\n4 - Add product\n0 - Exit");
    }
}
