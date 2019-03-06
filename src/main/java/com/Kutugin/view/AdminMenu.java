package com.Kutugin.view;

import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminMenu implements IMenu {
    private ClientService clientService;
    private ValidationService validator;
    private BufferedReader br;
    private ProductServise productService;

    public AdminMenu(BufferedReader br, ClientService clientService, ValidationService validator, ProductServise productService) {
        this.br = br;
        this.clientService = clientService;
        this.validator = validator;
        this.productService = productService;
    }

    public void show() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1 - Show clients\n2 - Add client\n3 - Modify client\n4 - Remove client\n5 - Show products\n6 - Add product\n7 - Modify product\n8 - Delete product\n0 - Exit to main menu");
            String inputPhoneNumber;
            switch (getInput()) {
                case "1":
                    showAllClients();
                    break;
                case "2":
                    createClient();
                    break;
                case "3":
                    System.out.println("Input Client phone number:");
                    inputPhoneNumber = getInput();
                    if (clientService.contains(inputPhoneNumber)) {
                        System.out.println("What you want to modify?:\n1 - Name\n2 - Surname\n3 - Age\n4 - Email\n5 - Phone Number");
                        String input = null;
                        input = getInput();
                        switch (input) {
                            case "1": {
                                System.out.println("Enter new name:");
                                input = getInput();
                                try {
                                    validator.validateName(input);
                                    clientService.updateClient(inputPhoneNumber, 1, input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Enter new surname:");
                                input = getInput();
                                try {
                                    validator.validateName(input);
                                    clientService.updateClient(inputPhoneNumber, 2, input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "3": {
                                input = getInput();
                                try {
                                    validator.validateAge(input);
                                    clientService.updateClient(inputPhoneNumber, 3, input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "4": {
                                input = getInput();
                                try {
                                    validator.validateEmail(input);
                                    clientService.updateClient(inputPhoneNumber, 4, input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                            case "5": {
                                input = getInput();
                                try {
                                    validator.validatePhoneNumber(input);
                                    clientService.updateClient(inputPhoneNumber, 5, input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            }
                        }
                    } else
                        System.out.println("Client not found");
                    break;
                case "4":
                    System.out.println("Input Client phone number(id):");
                    inputPhoneNumber = getInput();
                    if (clientService.contains(inputPhoneNumber)) {
                        clientService.deleteClient(clientService.getByPhoneNumber(inputPhoneNumber));
                        System.out.println("Client removed");
                        break;
                    }
                    System.out.println("Client not found");
                    break;
                case "5":
                    showProducts();
                    break;
                case "6":
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
                        productService.saveProduct(new Product(firm, model, price, type));
                    } else {
                        System.out.println("Abort, no such Product type");
                    }
                    break;
                case "7":
                    modifyProduct();
                    break;
                case "8":
                    deleteProduct();
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

    private void modifyProduct() {
        int j = 0;
        for (Product product : productService.getProducts()) {
            System.out.println(++j + " - " + product);
        }
        System.out.println("1-" + j + " - modify product\nr - return");
        while (true) {
            String input = null;
            switch (input = getInput()) {
                case "r":
                    return;
                default:
                    try {
                        int index = Integer.valueOf(input) - 1;
                        System.out.println(productService.getProducts().get(index));
                        System.out.println("What you want to modify?\n1 - firm\n2 - model\n3 - type\n4 - price");
                        switch (input = getInput()) {

                            case "1":
                                System.out.println("Enter new firm");
                                input = getInput();
                                productService.updateProduct(productService.getProducts().get(index).getId(), 1, input);
                                break;
                            case "2":
                                System.out.println("Enter new model");
                                input = getInput();
                                productService.updateProduct(productService.getProducts().get(index).getId(), 2, input);
                                break;
                            case "3":
                                System.out.println("Enter new type");
                                input = getInput();
                                productService.updateProduct(productService.getProducts().get(index).getId(), 3, input);
                                break;
                            case "4":
                                System.out.println("Enter new price");
                                //verify
                                input = getInput();
                                productService.updateProduct(productService.getProducts().get(index).getId(), 4, input);
                                break;
                            default:
                                System.out.println("Wrong input!");
                        }
                        System.out.println("Product updated");
                    } catch (NumberFormatException ex) {
                        System.out.println("Wrong input!");
                    }
                    break;
            }
            System.out.println("r - return");
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
        System.out.println("Create client:");
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
            clientService.createClient(name, surname, age, email, phoneNumber);
            System.out.println("New Client created!");
        } catch (BusinessException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Client not created!");
            return;
        }
    }

    private void showAllClients() {
        System.out.println("Clients:");
        clientService.getAllClients().forEach(System.out::println);
    }

    private void showProducts() {
        for (Product product : productService.getProducts()) {
            System.out.println(product);
        }
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