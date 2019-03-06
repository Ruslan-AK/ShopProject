package com.Kutugin;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.OrderDao;
import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.H2DB.ClientDBDao;
import com.Kutugin.dao.impl.H2DB.OrderDBDao;
import com.Kutugin.dao.impl.H2DB.ProductDBDao;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductServise;
import com.Kutugin.services.impl.ClientServiceImpl;
import com.Kutugin.services.impl.OrderServiceImpl;
import com.Kutugin.services.impl.ProductServiceImpl;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;
import com.Kutugin.view.AdminMenu;
import com.Kutugin.view.ClientMenu;
import com.Kutugin.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {
        ClientDao clientDao = new ClientDBDao();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
//        ProductDao productDao = ProductDaoImpl.getInstance();
        ProductDao productDao = new ProductDBDao();

        ProductServise productService = new ProductServiceImpl(productDao);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService, validationService, productService);
        OrderDao orderDao = new OrderDBDao();
        OrderService orderService = new OrderServiceImpl(orderDao);
        ClientMenu clientMenu = new ClientMenu(productService, br, clientService, validationService, orderService);
        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}