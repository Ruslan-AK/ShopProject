package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.services.impl.ProductServiceImpl;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class ClientMenu {

    private BufferedReader br;
    private ClientService clientService;
    private ClientAuthentication clientAutentification;
    private Client currentClient;
    private ProductServise productService = new ProductServiceImpl();
    private boolean signIn = false;
    private ValidationService validator = new ValidationServiceImpl();

    public ClientMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
        clientAutentification = new ClientAuthentication(br,clientService);
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning){
            if (signIn){
                System.out.println("1 - Show products\n2 - My cart\n3 - My account\n0 - Exit to main menu");
                switch (br.readLine()){
                    case "1":
                        System.out.println("show products");
                        int i=0;
                        for (Product product:productService.getProducts()){
                            System.out.println(++i +" - " + product);
                        }
                        System.out.println("1-" + i + " - bye item\nr - return");
                        //verificate
                        String input = null;
                        switch (input = br.readLine()){
                            case "r":
                                isRunning = false;
                                break;
                            default:
                                Product product = productService.getProducts().get(Integer.valueOf(input)-1);
                                Order order = currentClient.getOrder();
                                order.addProduct(product);
                                System.out.println("You bye "+ product);
                                break;
                        }
                        break;
                    case "2":
                        System.out.println("My cart");
                        break;
                    case "3":
                        System.out.println("my account");
                        break;
                    case "0":
                        System.out.println("Exit to main menu");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
            else {
                System.out.println("1 - Register\n2 - Login\n3 - Delete\n4 - Modify\n5 - Return\n0 - Exit to main menu");
                switch (br.readLine()){
                    case "1":
                        createClient();
                        System.out.println("New Client created!");
                        break;
                    case "2":
                        currentClient = clientAutentification.login();
                        if (currentClient!=null)
                            signIn = true;
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
                        modify();
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
    }

    private void modify() {

    }

    private void createClient() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input age:");
        int age = Integer.valueOf(br.readLine());
        System.out.println("Input email:");
        String email = br.readLine();
        System.out.println("Input phone:");
        String phoneNumber = br.readLine();
        try {
            validator.validatePhoneNumber(phoneNumber);//validator
        } catch (BusinessException ignored) {
        }
        clientService.createClient(name,surname, age, email,phoneNumber);
    }
}
