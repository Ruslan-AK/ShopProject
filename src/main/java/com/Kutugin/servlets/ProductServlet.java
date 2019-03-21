package com.Kutugin.servlets;

import com.Kutugin.domain.Product;
import com.Kutugin.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductServlet extends HttpServlet {

    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firm = req.getParameter("firm");
        String model = req.getParameter("model");
        double price = Double.valueOf(req.getParameter("price"));
        String type = req.getParameter("type");
        long id = Long.valueOf(req.getParameter("id"));
        Product product = new Product(firm, model, price, type);
        productService.updateProduct(id, product);
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("put".equals(req.getParameter("_method"))) {
            doPut(req, resp);
            return;
        }
        if ("delete".equals(req.getParameter("_method"))) {
            doDelete(req, resp);
            return;
        }
        String firm = req.getParameter("firm");
        String model = req.getParameter("model");
        double price = Double.valueOf(req.getParameter("price"));
        String type = req.getParameter("type");
        productService.saveProduct(new Product(firm, model, price, type));
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        productService.deleteById(id);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Product product : productService.getProducts()) {
            writer.println("<h3>" + product + "</h3>");
            writer.println("<br>");
        }
    }
}
