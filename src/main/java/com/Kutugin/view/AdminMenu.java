package com.Kutugin.view;

import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.ProductDaoImpl;
import com.Kutugin.domain.Product;
import com.Kutugin.domain.Products;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class AdminMenu {
    private BufferedReader br;
    private ClientService clientService;

    public AdminMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning){
            showMenu();
            switch (br.readLine()){
                case "1":
                    showAllClients();
                    break;
                case "2":
                    createClient();
                    break;
                case "3":
                    System.out.println("Input Client id:");
                    long id = Long.valueOf(br.readLine());
                    if(clientService.contains(id)){
                        System.out.println("Client found");
                        System.out.println("Input name:");
                        String uName = br.readLine();
                        System.out.println("Input surname:");
                        String uSurname = br.readLine();
                        System.out.println("Input phone:");
                        String uPhoneNumber = br.readLine();
                        clientService.updateClient(clientService.getById(id),uName,uSurname, uPhoneNumber);
                    }
                    System.out.println("Client not found");
                    break;
                case "4":

                    System.out.println("Input Client id:");
                    long rId = Long.valueOf(br.readLine());
                    if(clientService.contains(rId)){
                        clientService.deleteClient(clientService.getById(rId));
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
                    for(Products p:Products.values()) {
                        System.out.println(i++ +" - "+p.toString());
                    }
                    int pTypeI = Integer.valueOf(br.readLine());
                    int tmp = 0;
                    Products[] pro = Products.values();
                    System.out.println(pro.length);
                    for(Products product:pro){
                        tmp++;
                        if (tmp>=pro.length+1){
                            System.out.println("Abort, no such Product type");
                            break;
                        }
                        if(product.equals(pro[pTypeI-1])){
                            ProductDao productDao = new ProductDaoImpl();
                            productDao.saveProduct(new Product(pName, price,product));
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
        int age = readInteger();
        System.out.println("Input phone:");
        String phoneNumber = br.readLine();
        System.out.println("Input email:");
        String email = br.readLine();
        clientService.createClient(name,surname,age, phoneNumber,email);
    }

    private int readInteger() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (IOException|NumberFormatException ex){
            System.out.println("Wrong input");
            return readInteger();
        }
    }

    private void showAllClients(){
        clientService.getAllClients().forEach(System.out::println);
    }
}
