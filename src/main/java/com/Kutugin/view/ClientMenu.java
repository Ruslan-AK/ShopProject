package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientMenu {

    private BufferedReader br;
    private ClientService clientService;
    private Client currentClient;

    public ClientMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        System.out.println("1 - Register\n2 - Login\n3 - Delete\n4 - Modify\n5 - Return\n0 - Exit to main menu");
        while (isRunning){
            switch (br.readLine()){
                case "1":
                    if (createClient()) System.out.println("Client successfully created");
                    else System.out.println("Client already exist");
                    isRunning = false;
                    break;
                case "2":
                    System.out.println("Input id:");
                    //verification
                    currentClient = clientService.getById(Long.valueOf(br.readLine()));
                    if (currentClient == null) System.out.println("Client not found");
                    else System.out.println("Successfully login as "+currentClient);
                    //3 - Delete 4 - Modify List of products Order

                    break;
                case "3":
                    System.out.println("Input Client id:");
                    long rId = Long.valueOf(br.readLine());
                    if(clientService.contains(rId)){
                        clientService.deleteClient(clientService.getById(rId));
                        System.out.println("Client removed");
                        break;
                    }
                    System.out.println("Client not found");
                    break;
                case "4":
                    System.out.println("Modify");
                    break;
                case "5":
                    System.out.println("Return");
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

    private boolean createClient() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input phone:");
        String phoneNumber = br.readLine();
        if (clientService.createClient(name, surname, phoneNumber)) return true;
        else return false;
    }
}
