package com.Kutugin.servlets;

import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;
import com.Kutugin.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductServlet extends HttpServlet {

    private ProductService productService;
    private Product currentProduct;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("update".equals(req.getParameter("_method"))) {
            updateProduct(req, resp);
            return;
        }
        if ("delete".equals(req.getParameter("_method"))) {
            deleteProduct(req, resp);
            return;
        }
        if ("create".equals(req.getParameter("_method"))) {
            createProduct(req, resp);
            return;
        }
        if ("updateProductBlank".equals(req.getParameter("_method"))) {
            updateProductBlank(req, resp);
            return;
        }
        if ("enterProductID".equals(req.getParameter("_method"))) {
            enterProductID(req, resp);
            return;
        }
        String firm = req.getParameter("firm");
        String model = req.getParameter("model");
        double price = Double.valueOf(req.getParameter("price"));
        String type = req.getParameter("type");
        productService.saveProduct(new Product(firm, model, price, type));
        doGet(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Create Product</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/products\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"_method\" value=\"create\" />\n" +
                "    <fieldset>\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Firm\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"firm\"/> \n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Model\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"model\"/>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Price\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"price\"/>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Select Type from following:\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <select name=\"type\">\n" +
                productsTypeString() +
                "                    </select>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <input type=\"submit\" value=\"Create product\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </fieldset>\n" +
                "</form>\n" +
                "<form action=\"/Admin/adminProductMenu.html\">\n" +
                "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>"
        );
    }

    private void enterProductID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long productID = Long.valueOf(req.getParameter("productID"));
        currentProduct = productService.getByID(productID);
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Update Product</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<form action=\"/products\" method=\"post\">\n" +
                        "    <input type=\"hidden\" name=\"_method\" value=\"update\" />\n" +
                        "    <fieldset>\n" +
                        "        <table>\n" +
                        "            <tbody>\n" +
                        "            <tr>\n" +
                        "                <td>\n" +
                        "                    Firm\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <input type=\"text\" name=\"firm\" value=\"" + currentProduct.getFirm() + "\"/> \n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td>\n" +
                        "                    Model\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <input type=\"text\" name=\"model\" value=\"" + currentProduct.getModel() + "\"/>\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td>\n" +
                        "                    Price\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <input type=\"text\" name=\"price\" value=\"" + currentProduct.getPrice() + "\"/>\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td>\n" +
                        "                    Select Type from following:\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <select name=\"type\">\n" +
                                                productsTypeString()+
                        "                    </select>\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "            <tr>\n" +
                        "                <td>\n" +
                        "                    <input type=\"submit\" value=\"Update product\">\n" +
                        "                </td>\n" +
                        "            </tr>\n" +
                        "            </tbody>\n" +
                        "        </table>\n" +
                        "    </fieldset>\n" +
                        "</form>\n" +
                        "<form action=\"/Admin/adminProductMenu.html\">\n" +
                        "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>"
        );
    }

    private void updateProductBlank(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Update Product</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/products\" method=\"post\">\n" +
                "    <input type=\"hidden\" name=\"_method\" value=\"updateProductBlank\" />\n" +
                "    <fieldset>\n" +
                "        <table>\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Id\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"id\"/>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Firm\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"firm\"/>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Model\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"model\"> <br>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Price\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"price\"> <br>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    Select Type from following:\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <select name=\"type\">\n" +
                productsTypeString() +
                "                    </select>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <input type=\"submit\" value=\"Update product\">\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </fieldset>\n" +
                "</form>\n" +
                "<form action=\"/Admin/adminProductMenu.html\">\n" +
                "    <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        currentProduct = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Show clients</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<fieldset\n>");
        if (productService.getProducts().size() > 0) {
            for (Product product : productService.getProducts()) {
                writer.println("<h4>" + product + "</h4>");
            }
        } else {
            writer.println("<h4>Product list empty<h4>");
        }
        writer.println("<form action=\"/Admin/adminProductMenu.html\">\n" +
                "                 <input type=\"submit\" value=\"Back to menu\"/>\n" +
                "       </form>" +
                " </fieldset>\n" +
                "</body>\n" +
                "</html>");
    }

    private String productsTypeString() {
        StringBuilder sb = new StringBuilder();
        for (ProductType p : ProductType.values()) {
            if (p.toString().equals(currentProduct.getType()) && currentProduct != null) {
                sb.append("<option value=\"" + p.toString() + "\"selected>" + p.toString() + "</option>");
            } else {
                sb.append("<option value=\"" + p.toString() + "\">" + p.toString() + "</option>");
            }
        }
        return sb.toString();
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        productService.deleteById(id);
        doGet(req, resp);
    }
    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firm = req.getParameter("firm");
        String model = req.getParameter("model");
        double price = Double.valueOf(req.getParameter("price"));
        String type = req.getParameter("type");
        Product product = new Product(firm, model, price, type);
        productService.updateProduct(currentProduct.getId(), product);
        currentProduct = null;
        doGet(req, resp);
    }
}
