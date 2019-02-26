package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientAuthentication {
    private BufferedReader br;
    private ClientService clientService;
    private Client currentClient;
    private boolean isSignIn = false;
    private ValidationService validator;

    public ClientAuthentication(BufferedReader br, ClientService clientService, ValidationService validator) {
        this.br = br;
        this.clientService = clientService;
        this.validator = validator;
    }

    public Client getCurrentClient() {
        if (currentClient == null)
            login();
        return currentClient;
    }

    public Client login() {
        String input = null;
        if (isSignIn) {
            System.out.println("You already sign in\n1 - sign out\nr - Return to previous menu");
            try {
                input = br.readLine();
                currentClient = isRegistered(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (input) {
                case "1":
                    signOut();
                case "r":
                    break;
            }
        } else {
            System.out.println("Enter your mobile number");
            try {
                input = br.readLine();
                validator.validatePhoneNumber(input);
                currentClient = isRegistered(input);
                if (currentClient == null) {
                    System.out.println("Client not found");
                } else {
                    System.out.println("You sing in as:" + currentClient.getName());
                    isSignIn = true;
                }
            } catch (IOException | BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
        return currentClient;
    }

    public Client signOut() {
        isSignIn = false;
        return currentClient = null;
    }

    private Client isRegistered(String input) {
        for (Client client : clientService.getAllClients()) {
            if (client.getPhoneNumber().equals(input)) return client;
        }
        return null;
    }
}