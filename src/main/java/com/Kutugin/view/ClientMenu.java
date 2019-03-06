package com.Kutugin.view;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ClientMenu implements IMenu {

    private BufferedReader br;
    private ClientService clientService;
    private Client currentClient;
    private Order currentOrder;
    private List<Order> clientOrders;
    private ProductServise productService;
    private boolean signIn = false;
    private ValidationService validator;
    private OrderService orderService;

    public ClientMenu(ProductServise productService, BufferedReader br, ClientService clientService, ValidationService validator, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.validator = validator;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() {
        boolean isRunning = true;
        while (isRunning) {
            if (signIn) {
                System.out.println("1 - Show products\n2 - My order\n3 - My account\n4 - Log Out\n5 - Show order archive\nr - return");
                switch (getInput()) {
                    case "1":
                        showProducts();
                        break;
                    case "2":
                        myOrder();
                        break;
                    case "3":
                        isRunning = myAccount();
                        break;
                    case "4":
                        isRunning = signOut();
                        break;
                    case "5":
                        for (Order o : clientOrders) {
                            System.out.println(o);
                        }
                        break;
                    case "r":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Wrong input!");
                }
            } else {
                System.out.println("1 - Register\n2 - Login\nr - Return");
                switch (getInput()) {
                    case "1":
                        createClient();
                        break;
                    case "2":
                        currentClient = login();
                        if (currentClient != null) {
                            signIn = true;
                            clientOrders = orderService.getOrdersByClient(currentClient.getId());
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

    private boolean myAccount() {
        boolean run = true;
        while (run) {
            System.out.println("0 - Show Account Info\n1 - Modify\n2 - Delete\nr - Return");
            switch (getInput()) {
                case "r":
                    run = false;
                    break;
                case "0":
                    System.out.println(currentClient);
                    break;
                case "1":
                    modify();
                    break;
                case "2": {
                    boolean run1 = true;
                    while (run1) {
                        System.out.println("Are you sure?\n y - yes\n n - no");
                        String inputPhoneNumber = getInput();
                        switch (inputPhoneNumber) {
                            case "y":
                                clientService.deleteClient(currentClient);
                                System.out.println("Client removed");
                                currentClient = null;
                                signOut();
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


    private void myOrder() {
        if (currentOrder == null) {
            System.out.println("Order empty");
            return;
        }
        System.out.println(currentOrder);
        System.out.println("Total price: " + orderService.summaryPrice(currentOrder.getId()));
        boolean run = true;
        while (run) {
            System.out.println("r - return");
            String input = getInput();
            switch (input) {
                case "r":
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void showProducts() {
        int i = 0;
        for (Product product : productService.getProducts()) {
            System.out.println(++i + " - " + product);
        }
        System.out.println("1-" + i + " - bye item\nr - return");
        String input = null;
        switch (input = getInput()) {
            case "r":
                break;
            default:
                try {
                    int index = Integer.valueOf(input) - 1;
                    Product product = productService.getProducts().get(index);
                    System.out.println("You bye " + product);
                    if (currentOrder == null) {
                        currentOrder = new Order();
                        clientOrders.add(currentOrder);
                        orderService.add(currentClient, currentOrder);
                    }
                    orderService.addProduct(currentOrder.getId(), product);
                } catch (NumberFormatException ex) {
                    System.out.println("Wrong input!");
                }
                break;
        }
    }

    private void modify() {

        System.out.println("What you want to modify?:\n1 - Name\n2 - Surname\n3 - Age\n4 - Email\n5 - Phone Number\nr - Return");
        String input = null;
        input = getInput();
        switch (input) {
            case "r":
                break;
            case "1": {
                System.out.println("Enter new Name:");
                input = getInput();
                try {
                    validator.validateName(input);
                } catch (BusinessException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                clientService.updateClient(currentClient.getPhoneNumber(), 2, input);
                System.out.println("Client modified!");
                break;
            }
            case "2": {
                System.out.println("Enter new Surname:");
                input = getInput();
                try {
                    validator.validateName(input);
                } catch (BusinessException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                clientService.updateClient(currentClient.getPhoneNumber(), 2, input);
                System.out.println("Client modified!");
                break;
            }
            case "3": {
                System.out.println("Enter new Age:");
                input = getInput();
                try {
                    validator.validateAge(input);
                } catch (BusinessException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                clientService.updateClient(currentClient.getPhoneNumber(), 3, input);
                System.out.println("Client modified!");
                break;
            }
            case "4": {
                System.out.println("Enter new Email:");
                input = getInput();
                try {
                    validator.validateEmail(input);
                } catch (BusinessException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                clientService.updateClient(currentClient.getPhoneNumber(), 4, input);
                System.out.println("Client modified!");
                break;
            }
            case "5": {
                System.out.println("Enter new Phone Number:");
                input = getInput();
                try {
                    validator.validatePhoneNumber(input);
                } catch (BusinessException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                clientService.updateClient(currentClient.getPhoneNumber(), 5, input);
                System.out.println("Client modified!");
                break;
            }
            default:
                System.out.println("Wrong input");
                break;
        }

    }

    private void createClient() {
        System.out.println("Input name:");
        String name = getInput();
        try {
            validator.validateName(name);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input surname:");
        String surname = getInput();
        try {
            validator.validateName(surname);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input age:");
        String age = getInput();
        try {
            validator.validateAge(age);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input email:");
        String email = getInput();
        try {
            validator.validateEmail(email);
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
        System.out.println("Input phone:");
        String phoneNumber = getInput();
        try {
            validator.validatePhoneNumber(phoneNumber);//validator
            if (clientService.contains(phoneNumber)) {
                System.out.println("Client with this phone number already exist");
                System.out.println("Client not created!");
                return;
            }
            clientService.createClient(name, surname, age, email, phoneNumber);
            System.out.println("New Client created!\nYou can login now");
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
    }

    public Client login() {
        String input = null;
        if (signIn) {
            System.out.println("You already sign in\n1 - sign out\nr - Return to previous menu");
            try {
                input = br.readLine();
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
                currentClient = clientService.getByPhoneNumber(input);
                if (currentClient == null) {
                    System.out.println("Client not found");
                } else {
                    System.out.println("You sing in as:" + currentClient.getName());
                    signIn = true;
                }
            } catch (IOException | BusinessException e) {
                System.out.println(e.getMessage());
            }
        }
        return currentClient;
    }

    public boolean signOut() {
        signIn = false;
        currentClient = null;
        return false;
    }

    @Override
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
