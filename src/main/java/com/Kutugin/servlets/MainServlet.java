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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("login".equals(req.getParameter("_method"))) {
            login(req, resp);
            return;
        }
        if ("buyProduct".equals(req.getParameter("_method"))) {
            buyProduct(req, resp);
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
        if ("modifyCurrentClient".equals(req.getParameter("_method"))) {
            modifyCurrentClient(req, resp);
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
    }

    private void modifyCurrentClient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Update Client</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "        <form action=\"/main\" method=\"post\">\n" +
                "            <input type=\"hidden\" name=\"_method\" value=\"myAccount\"/>\n" +
                "            <input type=\"hidden\" name=\"_method1\" value=\"myAccountModify\"/>\n" +
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
                "                    <form action=\"/Client/myAccount.html\">\n" +
                "                        <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "                    </form>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "</body>\n" +
                "</html>");
    }

    private void showOrderArchive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Order Archive</title>\n" +
                "</head>\n" +
                "<body>");
        writer.println("<h3>Order Archive:</h3>");
        for (Order o : orderService.getOrdersByClient(currentClient.getId())) {
            writer.println(o);
        }
        writer.println("<form action=\"/Client/clientMenu.html\">\n" +
                "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "</form>");
        writer.println("</body>\n" +
                "</html>");
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
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Delete Client</title>\n" +
                        "</head>\n" +
                        "<body>");
                writer.println("<h3>Client Info:</h3><br>" +"<em>"+ currentClient+"</em>");
                writer.println("<form action=\"/Client/clientMenu.html\">\n" +
                        "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                        "</form>");
                writer.println("</body>\n" +
                        "</html>");
                break;
            }
            case "myAccountModify": {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String age = req.getParameter("age");
                String email = req.getParameter("email");
                String phone = null;
                clientService.updateClient(currentClient.getId(), new Client(currentClient.getId(), name, surname, age, email, phone));
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Update Client</title>\n" +
                        "</head>\n" +
                        "<body>");
                writer.println("<h3>Client updated:</h3>");
                writer.println(currentClient = clientService.getClientByID(currentClient.getId()));
                writer.println("<form action=\"/Client/clientMenu.html\">\n" +
                        "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                        "</form>");
                writer.println("</body>\n" +
                        "</html>");
                break;
            }
            case "myAccountDelete": {
                clientService.deleteClient(currentClient.getId());
                currentClient = null;
                currentOrder = null;
                signIn = false;
                resp.sendRedirect("mainMenu.html");
                break;
            }
        }
    }

    private void buyProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.valueOf(req.getParameter("id"));
        if (currentOrder == null) {
            currentOrder = new Order(orderService.getNextByMaxID());
            orderService.addClientOrder(currentClient, currentOrder);
        }
        Product goods = productService.getByID(id);
        currentOrder.addProduct(goods);
        orderService.update(currentOrder.getId(), currentOrder);
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Purchase</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <fieldset>\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                <h2>You buy " + goods.getFirm() + " " + goods.getModel() + "</h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </fieldset>\n" +
                "<form action=\"/Client/showBuyProducts.html\">\n" +
                "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    private void myOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        if (currentOrder == null) {
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Purchase</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <fieldset>\n" +
                    "        <table>\n" +
                    "            <tbody>\n" +
                    "            <tr>\n" +
                    "                <td>\n" +
                    "                <h2>Order empty</h2>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            </tbody>\n" +
                    "        </table>\n" +
                    "    </fieldset>\n" +
                    "<form action=\"/Client/clientMenu.html\">\n" +
                    "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
            return;
        }
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Purchase</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <fieldset>\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                <h2>Current Order:</h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                                currentOrder +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                <h2>Total price:</h2>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                                    orderService.summaryPrice(currentOrder) +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </fieldset>\n" +
                "<form action=\"/Client/clientMenu.html\">\n" +
                "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

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
        } else {
            currentClient = clientService.getClientByID(clientService.getIDByPhoneNumber(phone));
            if (currentClient == null) {
                writer.println("<h2>Client not found</h2>");
                resp.sendRedirect("mainMenu.html");
            } else {
                writer.println("<h2>You sing in as:" + currentClient.getName() + "</h2>");
                resp.sendRedirect("/Client/clientMenu.html");
                signIn = true;
            }
        }
    }
}
