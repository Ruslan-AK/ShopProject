package com.Kutugin.servlets;

import com.Kutugin.dao.impl.H2DB.ClientDBDao;
import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.impl.ClientServiceImpl;
import com.Kutugin.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {

    private ClientService clientService = new ClientServiceImpl(new ClientDBDao(), new ValidationServiceImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientService.getAllClients();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Client client : clientService.getAllClients()) {
            writer.println("<h1>" + client + "</h1>");
        }
    }
}
