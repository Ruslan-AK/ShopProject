package com.Kutugin.view;

import com.Kutugin.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientAutentification {
    private BufferedReader br;
    private ClientService clientService;
    public ClientAutentification(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public void login(){
        boolean isRunning = true;
        while (isRunning){
            System.out.println("Enter your mobile number");
            String input = null;
            try {
                input = br.readLine();
                //verificate
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientService.getAllClients()
        }
    }
}
