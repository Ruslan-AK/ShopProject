package com.Kutugin.servlets;

import com.Kutugin.dao.ClientDao;
import com.Kutugin.dao.impl.H2DB.ClientDBDao;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.impl.ClientServiceImpl;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;

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
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao,validationService);
        ServletContext servletContext = sce.getServletContext();
        servletContext
                .addServlet("ClientServlet",new ClientServlet(clientService ))
                .addMapping("/clients/*");
    }
}
