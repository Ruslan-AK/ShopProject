package com.Kutugin.servlets;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductService;
import com.Kutugin.validators.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    boolean signIn = false;
    ValidationService validationService;
    ClientService clientService;
    ProductService productService;
    OrderService orderService;
    Client currentClient;
    Order currentOrder;

    public MainServlet(OrderService orderService, ProductService productService,ClientService clientService,ValidationService validationService) {
        this.orderService = orderService;
        this.productService = productService;
        this.validationService = validationService;
        this.clientService = clientService;
    }
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String firm = req.getParameter("firm");
//        String model = req.getParameter("model");
//        double price = Double.valueOf(req.getParameter("price"));
//        String type = req.getParameter("type");
//        long id = Long.valueOf(req.getParameter("id"));
//        Product product = new Product(firm, model, price, type);
//        productService.updateProduct(id, product);
//        doGet(req, resp);
//    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("login".equals(req.getParameter("_method"))) {
            login(req, resp);
            return;
        }
        if ("byeProduct".equals(req.getParameter("_method"))) {
            byeProduct(req, resp);
            return;
        }
        if ("myOrder".equals(req.getParameter("_method"))) {
            myOrder(req, resp);
            return;
        }
        if ("myAccount".equals(req.getParameter("_method"))) {
            myAccount(req, resp);
            return;
        }
        if ("logOut".equals(req.getParameter("_method"))) {
            logOut(req, resp);
            return;
        }
        if ("clientEnter".equals(req.getParameter("_method"))) {
            clientEnter(req, resp);
            return;
        }
        if ("showOrderArchive".equals(req.getParameter("_method"))) {
            showOrderArchive(req, resp);
            return;
        }
//        String firm = req.getParameter("firm");
//        String model = req.getParameter("model");
//        double price = Double.valueOf(req.getParameter("price"));
//        String type = req.getParameter("type");
//        productService.saveProduct(new Product(firm, model, price, type));
//        doGet(req, resp);
    }

    private void showOrderArchive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Order Archive:</h2>");
        for (Order o : orderService.getOrdersByClient(currentClient.getId())) {
            writer.println(o);
        }
        writer.println("<a href = /Client/clientMenu.html>Back to client menu</h2>");
    }

    private void clientEnter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (signIn) {
            resp.sendRedirect("/Client/clientMenu.html");
        } else {
            resp.sendRedirect("/Client/clientLoginMenu.html");
        }
    }

    private void myAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        switch (req.getParameter("_method1")) {
            case "myAccountInfo": {
                writer.println("<h2>Client Info:</h2><br>" + currentClient);
                break;
            }
            case "myAccountModify": {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String age = req.getParameter("age");
                String email = req.getParameter("email");
                String phone = null;
                clientService.updateClient(currentClient.getId(), new Client(currentClient.getId(), name, surname, age, email, phone));
                writer.println("<h2>Client updated:</h2>");
                writer.println(currentClient);
                writer.println("<a href=\"/Client/clientMenu.html\">Back to menu</a>");
                break;
            }
            case "myAccountDelete": {

                break;
            }
        }
//        boolean run = true;
//        while (run) {
//            System.out.println("0 - Show Account Info\n1 - Modify\n2 - Delete\nr - Return");
//            switch (getInput()) {
//                case "r":
//                    run = false;
//                    break;
//                case "0":
//                    System.out.println(currentClient);
//                    break;
//                case "1":
//                    modify();
//                    break;
//                case "2": {
//                    boolean run1 = true;
//                    while (run1) {
//                        System.out.println("Are you sure?\n y - yes\n n - no");
//                        String inputPhoneNumber = getInput();
//                        switch (inputPhoneNumber) {
//                            case "y":
//                                clientService.deleteClient(currentClient.getId());
//                                System.out.println("Client removed");
//                                currentClient = null;
//                                signOut();
//                                return false;
//                            case "n":
//                                run1 = false;
//                                break;
//                            default:
//                                System.out.println("Wrong input!");
//                                break;
//                        }
//                    }
//                }
//                default:
//                    System.out.println("Wrong input!");
//                    break;
//            }
        writer.println("<br><a href=\"/Client/myAccount.html\">Back to menu</a>");
    }

    private void byeProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.valueOf(req.getParameter("id"));
        if (currentOrder == null) {
            currentOrder = new Order(orderService.getNextByMaxID());
            orderService.addClientOrder(currentClient, currentOrder);
        }
        Product goods = productService.getByID(id);
        currentOrder.addProduct(goods);
        orderService.update(currentOrder.getId(), currentOrder);
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>You bye " + goods.getFirm() + " " + goods.getModel() + "</h2>");
        writer.println("<a href=\"/Client/showByeProducts.html\">Back to menu</a>");
    }

    private void myOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (currentOrder == null) {
            writer.println("<h2>Order empty</h2>");
            writer.println("<a href=\"/Client/clientMenu.html\">Back to menu</a>");
            return;
        }
        writer.println("<h2>Current Order</h2>");
        writer.println(currentOrder + "<br>");
        writer.println("Total price: " + orderService.summaryPrice(currentOrder) + "<br>");
        writer.println("<a href=\"/Client/clientMenu.html\">Back to menu</a>");
    }

//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long id = Long.valueOf(req.getParameter("id"));
//        productService.deleteById(id);
//        doGet(req, resp);
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        for (Order order : orderService.getOrdersByClient()) {
//            writer.println("<h3>" + product + "</h3>");
//            writer.println("<br>");
//        }
//    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        signIn = false;
        currentClient = null;
        currentOrder = null;
        resp.sendRedirect("mainMenu.html");
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phone = req.getParameter("phone");
        PrintWriter writer = resp.getWriter();
        if (signIn) {
            writer.println("<h2>You already sign in</h2>");
//            System.out.println("You already sign in\n1 - sign out\nr - Return to previous menu");
        } else {
            currentClient = clientService.getClientByID(clientService.getIDByPhoneNumber(phone));
            if (currentClient == null) {
                writer.println("<h2>Client not found</h2>");
            } else {
                writer.println("<h2>You sing in as:" + currentClient.getName() + "</h2>");
                writer.println("<a href=\"Client/clientMenu.html\">Client Menu>");
                signIn = true;
            }
        }
    }
}
