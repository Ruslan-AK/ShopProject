//package com.Kutugin.servlets;
//
//import com.Kutugin.domain.Order;
//import com.Kutugin.domain.Product;
//import com.Kutugin.services.OrderService;
//import com.Kutugin.services.ProductService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class OrderServlet extends HttpServlet {
//
//    private OrderService orderService;
//
//    public OrderServlet(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
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
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if ("login".equals(req.getParameter("_method"))) {
//            String phone = req.getParameter("phone");
//
//            return;
//        }
//        if ("delete".equals(req.getParameter("_method"))) {
//            doDelete(req, resp);
//            return;
//        }
//        String firm = req.getParameter("firm");
//        String model = req.getParameter("model");
//        double price = Double.valueOf(req.getParameter("price"));
//        String type = req.getParameter("type");
//        productService.saveProduct(new Product(firm, model, price, type));
//        doGet(req, resp);
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long id = Long.valueOf(req.getParameter("id"));
//        productService.deleteById(id);
//        doGet(req, resp);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        for (Order order : orderService.getOrdersByClient()) {
//            writer.println("<h3>" + product + "</h3>");
//            writer.println("<br>");
//        }
//    }
//}
