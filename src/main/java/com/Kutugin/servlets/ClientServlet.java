package com.Kutugin.servlets;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/clients")//may be used for mapping instead of web.xml
public class ClientServlet extends HttpServlet {

    private ClientService clientService;
    private Client currentClient;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Client client : clientService.getAllClients()) {
            writer.println("<h1>" + client + "</h1>");
            writer.println("<br>");
        }
        writer.println("<form action = \"/Admin/adminClientMenu.html\" >\n" +
                "                    <input type = \"submit\" value = \"Back to menu\" / >\n" +
                "       </form >");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("updateClient".equals(req.getParameter("_method"))) {
            updateClient(req, resp);
            return;
        }
        if ("delete".equals(req.getParameter("_method"))) {
            deleteClient(req, resp);
            return;
        }
        if ("adminClientLogin".equals(req.getParameter("_method"))) {
            adminClientLogin(req, resp);
            return;
        }
        if ("adminClientModify".equals(req.getParameter("_method"))) {
            adminClientModify(req, resp);
            return;
        }
        if ("createClient".equals(req.getParameter("_method"))) {
            createClient(req, resp);
            return;
        }
    }

    private void createClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, age, email, phone);
        doGet(req, resp);
    }

    private void adminClientModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (currentClient != null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String age = req.getParameter("age");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            clientService.updateClient(currentClient.getId(), currentClient = new Client(currentClient.getId(), name, surname, age, email, phone));
        }
        doGet(req, resp);
    }

    private void adminClientLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        String phone = req.getParameter("phone");
        currentClient = clientService.getClientByID(clientService.getIDByPhoneNumber(phone));
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Update Client</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "        <form action=\"/clients\" method=\"post\">\n" +
                "            <input type=\"hidden\" name=\"_method\" value=\"adminClientModify\"/>\n" +
                "            <fieldset>\n" +
                "                <table>\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            Name\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input type=\"text\" name=\"name\" value=\"" + currentClient.getName() + "\"/>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            Surname\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input type=\"text\" name=\"surname\" value=\"" + currentClient.getSurmame() + "\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            Age\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input type=\"text\" name=\"age\" value=\"" + currentClient.getAge() + "\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            Email\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input type=\"text\" name=\"phone\" value=\"" + currentClient.getPhoneNumber() + "\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            Email\n" +
                "                        </td>\n" +
                "                        <td>\n" +
                "                            <input type=\"text\" name=\"email\" value=\"" + currentClient.getEmail() + "\">\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </fieldset>\n" +
                "            <input type=\"submit\" value=\"Update client\">\n" +
                "        </form>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <form action=\"/Admin/adminClientMenu.html\">\n" +
                "                        <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "                    </form>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void updateClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        long id = clientService.getIDByPhoneNumber(phone);
        clientService.updateClient(id, new Client(id, name, surname, age, email, phone));
        doGet(req, resp);
    }

    protected void deleteClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        long id = clientService.getIDByPhoneNumber(phone);
        clientService.deleteClient(id);
        doGet(req, resp);
    }
}
