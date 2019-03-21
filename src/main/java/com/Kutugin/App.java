package com.Kutugin;

import com.Kutugin.view.MainMenu;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
//        InitDB init = new InitDB();
//        ClientDao clientDao = new ClientDBDao();
//        ValidationService validationService = new ValidationServiceImpl();
//        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
////        ProductDao productDao = ProductDaoImpl.getInstance();
//        ProductDao productDao = new ProductDBDao();
//
//        ProductService productService = new ProductServiceImpl(productDao);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        AdminMenu adminMenu = new AdminMenu(br, clientService, validationService, productService,init);
//        OrderDao orderDao = new OrderDBDao();
//        OrderService orderService = new OrderServiceImpl(orderDao);
//        ClientMenu clientMenu = new ClientMenu(productService, br, clientService, validationService, orderService);
//        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
//        menu.showMenu();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        MainMenu menu = (MainMenu) context.getBean("mainMenu");
        menu.showMenu();
    }
}