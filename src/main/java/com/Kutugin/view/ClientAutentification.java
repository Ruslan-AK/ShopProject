package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientAuthentification {
    private BufferedReader br;
    private ClientService clientService;
    private Client currentClient;
    private boolean isSignIn = false;

    public ClientAuthentification(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public Client getCurrentClient() {
        if (currentClient == null)
            login();
        return currentClient;
    }

    public void login() {
        boolean isRunning = true;
        String input = null;
        while (isRunning) {
            if (isSignIn){
                System.out.println("You already sign in\n1 - sign out\nr - Return to previous menu");
                try {
                    input = br.readLine();
                    currentClient = isRegistered(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (input){
                    case "1": signOut();
                    case "r": isRunning = false;
                        break;
                }
            }
            else {
                System.out.println("Enter your mobile number");
                try {
                    input = br.readLine();
                    currentClient = isRegistered(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isRegistered(input);
            }
        }
    }

    private void signOut() {
        currentClient = null;
    }

    private Client isRegistered(String input) {
        for(Client client:clientService.getAllClients()){
            if(client.getPhoneNumber().equals(input)) return client;
            return null;
        }
    }
}
