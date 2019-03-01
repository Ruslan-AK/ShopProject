package com.Kutugin.view;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.ProductDaoImpl;
import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;
import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.services.ClientService;
import com.Kutugin.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminMenu {
    private BufferedReader br;
    private ClientService clientService;
    private ValidationService validator;

    public AdminMenu(BufferedReader br, ClientService clientService, ValidationService validator) {
        this.br = br;
        this.clientService = clientService;
        this.validator = validator;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            String inputId;
            switch (br.readLine()) {
                case "1":
                    showAllClients();
                    break;
                case "2":
                    createClient();
                    break;
                case "3":
                    System.out.println("Input Client id:");
                    inputId = br.readLine();
                    if (clientService.contains(inputId)) {
                        System.out.println("What you want to modify?:\n1 - Name\n2 - Surname\n3 - Age\n4 - Email\n5 - Phone Number");
                        String input = null;
                        try {
                            input = br.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        switch (input) {
                            case "1": {
                                try {
                                    input = br.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                clientService.updateClient(clientService.getById(inputId), Integer.valueOf(input), input);
                                break;
                            }
                            case "2": {
                                try {
                                    input = br.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                clientService.updateClient(clientService.getById(inputId), Integer.valueOf(input), input);
                                break;
                            }
                            case "3": {
                                try {
                                    input = br.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    validator.validateAge(input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                clientService.updateClient(clientService.getById(inputId), Integer.valueOf(input), input);
                                break;
                            }
                            case "4": {
                                try {
                                    input = br.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    validator.validateEmail(input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                clientService.updateClient(clientService.getById(inputId), Integer.valueOf(input), input);
                                break;
                            }
                            case "5": {
                                try {
                                    input = br.readLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    validator.validatePhoneNumber(input);
                                } catch (BusinessException e) {
                                    System.out.println(e.getMessage());
                                }
                                clientService.updateClient(clientService.getById(inputId), Integer.valueOf(input), input);
                                break;
                            }
                        }
                    } else
                        System.out.println("Client not found");
                    break;
                case "4":

                    System.out.println("Input Client id:");
                    inputId = br.readLine();
                    if (clientService.contains(inputId)) {
                        clientService.deleteClient(clientService.getById(inputId));
                        System.out.println("Client removed");
                        break;
                    }
                    System.out.println("Client not found");
                    break;
                case "5":
                    System.out.println("Add product");
                    System.out.println("Enter product name:");
                    String pName = br.readLine();
                    System.out.println("Enter product price:");
                    BigDecimal price = BigDecimal.valueOf(Double.valueOf(br.readLine()));
                    System.out.println("Enter product type:");
                    int i = 1;
                    for (ProductType p : ProductType.values()) {
                        System.out.println(i++ + " - " + p.toString());
                    }
                    int pTypeI = Integer.valueOf(br.readLine());
                    int tmp = 0;
                    ProductType[] pro = ProductType.values();
                    System.out.println(pro.length);
                    for (ProductType product : pro) {
                        tmp++;
                        if (tmp >= pro.length + 1) {
                            System.out.println("Abort, no such Product type");
                            break;
                        }
                        if (product.equals(pro[pTypeI - 1])) {
                            ProductDao productDao = ProductDaoImpl.getInstance();
                            productDao.saveProduct(new Product(pName, price, product));
                            break;
                        }
                    }
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

    private void showMenu() {
        System.out.println("1 - Show clients\n2 - Add client\n3 - Modify client\n4 - Remove client\n5 - Add product\n0 - Exit to main menu");
    }

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
            System.out.println("Client not created!");
            return;
        }
    }

    private void showAllClients() {
        System.out.println("Clients:");
        clientService.getAllClients().forEach(System.out::println);
    }
}