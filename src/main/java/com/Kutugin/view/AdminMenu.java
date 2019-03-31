package com.Kutugin.view;

import com.Kutugin.dao.impl.H2DB.InitDB;
import com.Kutugin.domain.Client;
import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ProductService;
import com.Kutugin.validators.ValidationService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class AdminMenu {
    private ClientService clientService;
    private ValidationService validator;
    private BufferedReader br;
    private ProductService productService;
    private InitDB init;

    public AdminMenu(BufferedReader br, ClientService clientService, ValidationService validator, ProductService productService, InitDB init) {
        this.br = br;
        this.clientService = clientService;
        this.validator = validator;
        this.productService = productService;
        this.init = init;
    }

    public void show() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1 - Show clients\n2 - Add client\n3 - Modify client\n4 - Remove client\n5 - Show products\n6 - Add product\n7 - Modify product\n8 - Delete product\n9 - Initialize DB\n0 - Exit to main menu");
            switch (getInput()) {
                case "1":
                    showAllClients();
                    break;
                case "2":
                    createClient();
                    break;
                case "3":
                    modifyClient();
                    break;
                case "4":
                    deleteClient();
                    break;
                case "5":
                    showProducts();
                    break;
                case "6":
                    addProduct();
                    break;
                case "7":
                    modifyProduct();
                    break;
                case "8":
                    deleteProduct();
                    break;
                case "9":
                    init.createAndFill();
                    System.out.println("DB created, reload program, please");
                    System.exit(0);
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

    private void addProduct() {
        System.out.println("Add product");
        System.out.println("Enter product firm:");
        String firm = getInput();
        System.out.println("Enter product model:");
        String model = getInput();
        System.out.println("Enter product price:");
        double price = Double.valueOf(getInput());
        System.out.println("Enter product type from following:");
        for (ProductType p : ProductType.values()) {
            System.out.println(p.toString());
        }
        String type = getInput();
        if (isInEnum(type)) {
            Product product = new Product(firm, model, price, type);
            productService.saveProduct(product);
            System.out.println("Product added: \n" + product);
        } else {
            System.out.println("Abort, no such Product type");
        }
    }

    private void deleteClient() {
        String inputPhoneNumber;
        if (clientService.getAllClients().size() > 0) {
            System.out.println("Input Client phone number:");
            inputPhoneNumber = getInput();
            try {
                validator.validatePhoneNumber(inputPhoneNumber);
                if (clientService.isPresent(inputPhoneNumber)) {
                    clientService.deleteClient(clientService.getIDByPhoneNumber(inputPhoneNumber));
                    System.out.println("Client removed");
                } else System.out.println("Client not found");
            } catch (BusinessException e) {
                System.out.println(e.getMessage());
                System.out.println("Abort deleting");
            }
        }
    }

    private void modifyClient() {
        String inputPhoneNumber;
        System.out.println("Input Client phone number:");
        inputPhoneNumber = getInput();
        try {
            validator.validatePhoneNumber(inputPhoneNumber);
            long currentClientID = clientService.getIDByPhoneNumber(inputPhoneNumber);
            if (clientService.isPresent(clientService.getClientByID(currentClientID).getPhoneNumber())) {
                Client mockClient = new Client();
                boolean modFlag = true;
                while (modFlag) {
                    System.out.println("What you want to modify?:\n1 - Name\n2 - Surname\n3 - Age\n4 - Email\n5 - Phone Number\nr - return");
                    String input = getInput();
                    try {
                        switch (input) {
                            case "1": {
                                System.out.println("Enter new name:");
                                input = getInput();
                                validator.validateName(input);
                                mockClient.setName(input);
                                break;
                            }
                            case "2": {
                                System.out.println("Enter new surname:");
                                input = getInput();
                                validator.validateName(input);
                                mockClient.setSurname(input);
                                break;
                            }
                            case "3": {
                                System.out.println("Enter new age:");
                                input = getInput();
                                validator.validateAge(input);
                                mockClient.setAge(Integer.valueOf(input));
                                break;
                            }
                            case "4": {
                                System.out.println("Enter new email:");
                                input = getInput();
                                validator.validateEmail(input);
                                mockClient.setEmail(input);
                                break;
                            }
                            case "5": {
                                System.out.println("Enter new phone number1" +
                                        ":");
                                input = getInput();
                                validator.validatePhoneNumber(input);
                                mockClient.setPhoneNumber(input);
                                break;
                            }
                            case "r": {
                                modFlag = false;
                                break;
                            }
                        }
                    } catch (BusinessException e) {
                        System.out.println(e.getMessage());
                    }
                    clientService.updateClient(currentClientID, mockClient);
                }
            } else
                System.out.println("Client not found");
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            System.out.println("Abort");
        }

    }

    private void modifyProduct() {
        while (true) {
            int j = 0;
            for (Product product : productService.getProducts()) {
                System.out.println(++j + " - " + product);
            }
            System.out.println("Select product to modify");
            System.out.println("1-" + j + " - modify product\nr - return");
            String input = getInput();
            switch (input) {
                case "r": {
                    return;
                }
                default: {
                    try {
                        validator.validateInteger(input);
                        int index = Integer.valueOf(input) - 1;
                        long currentProductID = productService.getProducts().get(index).getId();
                        Product mockProduct = new Product();
                        System.out.println(productService.getByID(currentProductID));
                        boolean modFlag = true;
                        while (modFlag) {
                            System.out.println("What you want to modify?\n1 - firm\n2 - model\n3 - type\n4 - price\nr - return");
                            input = getInput();
                            switch (input) {
                                case "1":
                                    System.out.println("Enter new firm");
                                    input = getInput();
                                    mockProduct.setFirm(input);
                                    break;
                                case "2":
                                    System.out.println("Enter new model");
                                    input = getInput();
                                    mockProduct.setModel(input);
                                    break;
                                case "3":
                                    System.out.println("Enter new type");
                                    input = getInput();
                                    mockProduct.setType(input);
                                    break;
                                case "4":
                                    System.out.println("Enter new price");
                                    input = getInput();
                                    validator.validateDouble(input);
                                    mockProduct.setPrice(Double.valueOf(input));
                                    break;
                                case "r":
                                    modFlag = false;
                                    break;
                                default:
                                    System.out.println("Wrong input!");
                                    break;
                            }
                            if (modFlag) {
                                productService.updateProduct(currentProductID, mockProduct);
                                System.out.println("Product updated:\n" + productService.getByID(currentProductID));
                            }
                        }
                    } catch (BusinessException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private boolean isInEnum(String type) {
        for (ProductType p : ProductType.values()) {
            if (p.toString().equals(type))
                return true;
        }
        return false;
    }

    private void createClient() {
        System.out.println("Input data to create client:");
        try {
            System.out.println("Input name:");
            String name = getInput();
            validator.validateName(name);
            System.out.println("Input surname:");
            String surname = getInput();
            validator.validateName(surname);
            System.out.println("Input age:");
            String age = getInput();
            validator.validateAge(age);
            System.out.println("Input email:");
            String email = getInput();
            validator.validateEmail(email);
            System.out.println("Input phone:");
            String phoneNumber = getInput();
            validator.validatePhoneNumber(phoneNumber);//validator
            if (!clientService.isPresent(phoneNumber)) {
                clientService.createClient(name, surname, age, email, phoneNumber);
                System.out.println("New Client created!");
            } else System.out.println("Client with the same number already exist!");
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            System.out.println("Client not created!");
        }
    }

    private void showAllClients() {
        System.out.println("Clients:");
        clientService.getAllClients().forEach(System.out::println);
    }

    private void showProducts() {
        System.out.println("Products:");
        productService.getProducts().forEach(System.out::println);
        String input = null;
        while (true) {
            System.out.println("r - return");
            switch (input = getInput()) {
                case "r":
                    return;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private void deleteProduct() {
        while (true) {
            int j = 0;
            if (productService.getProducts().size() < 1) {
                System.out.println("No products");
                return;
            }
            for (Product product : productService.getProducts()) {
                System.out.println(++j + " - " + product);
            }
            System.out.println("1-" + j + " - delete product\nr - return");
            String input = null;
            switch (input = getInput()) {
                case "r":
                    return;
                default:
                    try {
                        int index = Integer.valueOf(input) - 1;
                        productService.deleteById(productService.getProducts().get(index).getId());
                        System.out.println("Product deleted");
                        j--;
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong input!");
                    }
                    break;
            }
        }
    }

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