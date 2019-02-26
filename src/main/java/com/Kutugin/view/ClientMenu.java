package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.services.impl.OrderServiceImpl;
import com.Kutugin.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientMenu {

    private BufferedReader br;
    private ClientService clientService;
    private ClientAuthentication clientAuthentication;
    private Client currentClient;
    private ProductServise productService;
    private boolean signIn = false;
    private ValidationService validator;
    private OrderService orderService = new OrderServiceImpl();
    private Map<String, Long> clientOrderIdMap = new HashMap<>();

    public ClientMenu(ProductServise productService, BufferedReader br, ClientService clientService, ClientAuthentication clientAuthentication, ValidationService validator) {
        this.br = br;
        this.clientService = clientService;
        this.clientAuthentication = clientAuthentication;
        this.validator = validator;
        this.productService = productService;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            if (signIn) {
                System.out.println("1 - Show products\n2 - My order\n3 - My account\nr - return");
                switch (br.readLine()) {
                    case "1":
                        showProducts();
                        break;
                    case "2":
                        myOrder();
                        break;
                    case "3":
                        isRunning = myAccount();
                        break;
                    case "r":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            } else {
                System.out.println("1 - Register\n2 - Login\nr - Return");
                switch (br.readLine()) {
                    case "1":
                        createClient();
                        break;
                    case "2":
                        currentClient = clientAuthentication.login();
                        if (currentClient != null){
                            signIn = true;
                            clientOrderIdMap.put(currentClient.getPhoneNumber(),orderService.add(new Order()));
                        }
                        break;
                    case "r":
                        System.out.println("Return");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            }
        }
    }

    private boolean myAccount() throws IOException {
        boolean run = true;
        while (run) {
            System.out.println("1 - Modify\n2 - Delete\nr - Return");
            switch (br.readLine()) {
                case "r":
                    run = false;
                    break;
                case "1":
                    //modify();
                    break;
                case "2": {
                    boolean run1 = true;
                    while (run1) {
                        System.out.println("Are you sure?\n y - yes\n n - no");
                        String inputId = br.readLine();
                        switch (inputId) {
                            case "y":
                                clientService.deleteClient(currentClient);
                                System.out.println("Client removed");
                                clientOrderIdMap.remove(currentClient.getPhoneNumber());
                                currentClient = clientAuthentication.signOut();
                                signIn = false;
                                return false;
                            case "n":
                                run1 = false;
                                break;
                            default:
                                System.out.println("Wrong input!");
                                break;
                        }
                    }
                }
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
        return true;
    }


    private void myOrder() throws IOException {
        if (clientOrderIdMap.get(currentClient.getPhoneNumber()) == null) {
            Order order = new Order();
            orderService.add(order);
            clientOrderIdMap.put(currentClient.getPhoneNumber(), order.getId());
        }
        System.out.println(orderService.getById(clientOrderIdMap.get(currentClient.getPhoneNumber())));
        boolean run = true;
        while (run){
            System.out.println("r - return");
            String input = br.readLine();
            switch (input){
                case "r":
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void showProducts() throws IOException {
        int i = 0;
        for (Product product : productService.getProducts()) {
            System.out.println(++i + " - " + product);
        }
        System.out.println("1-" + i + " - bye item\nr - return");
        //verificate
        String input = null;
        switch (input = br.readLine()) {
            case "r":
                break;
            default:
                Product product = productService.getProducts().get(Integer.valueOf(input) - 1);
                System.out.println("You bye " + product);
                orderService.getById(clientOrderIdMap.get(currentClient.getPhoneNumber())).addProduct(product);//get Order by Client id and add Product to Order
                break;
        }
    }

//    private void modify() {
//        clientService.updateClient();
//    }

    private void createClient() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input age:");
        String age = br.readLine();
        try {
            validator.validateAge(age);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input email:");
        String email = br.readLine();
        try {
            validator.validateEmail(email);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input phone:");
        String phoneNumber = br.readLine();
        try {
            validator.validatePhoneNumber(phoneNumber);//validator
            clientService.createClient(name, surname, age, email, phoneNumber);
            System.out.println("New Client created!");
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!\nYou can login now");
            return;
        }
    }
}
