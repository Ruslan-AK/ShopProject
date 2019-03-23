package com.Kutugin.servlets;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.OrderDao;
import com.Kutugin.dao.ProductDao;
import com.Kutugin.dao.impl.H2DB.ClientDBDao;
import com.Kutugin.dao.impl.H2DB.OrderDBDao;
import com.Kutugin.dao.impl.H2DB.ProductDBDao;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductService;
import com.Kutugin.services.impl.ClientServiceImpl;
import com.Kutugin.services.impl.OrderServiceImpl;
import com.Kutugin.services.impl.ProductServiceImpl;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;
import org.h2.tools.Server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClientDao clientDao = new ClientDBDao();
        ProductDao productDao = new ProductDBDao();
        OrderDao orderDao = new OrderDBDao();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao,validationService);
        ProductService productService = new ProductServiceImpl(productDao);
        OrderService orderService = new OrderServiceImpl(orderDao);
        ServletContext servletContext = sce.getServletContext();
        servletContext
                .addServlet("ClientServlet", new ClientServlet(clientService))
                .addMapping("/clients/*");
        servletContext
                .addServlet("ProductServlet", new ProductServlet(productService))
                .addMapping("/products/*");
        servletContext
                .addServlet("MainServlet", new MainServlet(orderService,productService,clientService,validationService))
                .addMapping("/main/*");
        try {
            Server.openBrowser("http://localhost:8080/mainMenu.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
