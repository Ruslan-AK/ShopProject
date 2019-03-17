package com.Kutugin.servlets;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {

    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, age, email, phone);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.valueOf(id));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String passInfo = req.getPathInfo();
        if ("/delete".equals(passInfo)) {
            doDelete(req, resp);
            return;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Client client : clientService.getAllClients()) {
            writer.println("<h1>" + client + "</h1>");
            writer.println("<br>");
        }
    }
}
