package com.Kutugin.controllers;

import com.Kutugin.domain.Product;
import com.Kutugin.domain.ProductType;
import com.Kutugin.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    private Product currentProduct;

    @RequestMapping(value = "showProducts", method = RequestMethod.POST)
    public String showProducts(ModelMap modelMap) {
        String productsString = "";
        if (productService.getProducts().size() > 0) {
            for (Product product : productService.getProducts()) {
                productsString += "<p>" + product.toString() + "</p>";
            }
        } else {
            productsString = "No products";
        }
        modelMap.put("message", productsString);
        return "/Admin/showProducts";
    }

    @RequestMapping(value = "createProductBlank", method = RequestMethod.POST)
    public String createProduct(ModelMap modelMap) {
        String productsTypesString = "";
        for (ProductType pt : ProductType.values()) {
            if (pt.toString().equals(currentProduct.getType())) {
                productsTypesString += "<option value=\"" + pt.toString() + "\" selected>" + pt.toString() + "</option>\n";
            } else {
                productsTypesString += "<option value=\"" + pt.toString() + "\">" + pt.toString() + "</option>\n";
            }
        }
        modelMap.put("options", productsTypesString);
        return "/Admin/createProduct";
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String createClient(
            @RequestParam String firm,
            @RequestParam String model,
            @RequestParam String price,
            @RequestParam String type,
            ModelMap modelMap) {
        Product product = new Product(firm, model, Double.valueOf(price), type);
        long id = productService.saveProduct(product);
        String s = productService.getByID(id).toString() + " - created";
        modelMap.put("productString", s);
        modelMap.put("back", "/Admin/adminProductMenu.jsp");
        return "/Admin/showProduct";
    }

    @RequestMapping(value = "/updateProductBlank", method = RequestMethod.POST)
    public String updateProductBlank(
            @RequestParam String id,
            ModelMap modelMap) {
        currentProduct = productService.getByID(Long.parseLong(id));
        String productsTypesString = "";
        for (ProductType pt : ProductType.values()) {
            if (pt.toString().equals(currentProduct.getType())) {
                productsTypesString += "<option value=\"" + pt.toString() + "\" selected>" + pt.toString() + "</option>\n";
            } else {
                productsTypesString += "<option value=\"" + pt.toString() + "\">" + pt.toString() + "</option>\n";
            }
        }
        modelMap.put("options", productsTypesString);
        modelMap.put("price", currentProduct.getPrice());
        modelMap.put("model", currentProduct.getModel());
        modelMap.put("firm", currentProduct.getFirm());
        return "/Admin/updateProductForm";
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(
            @RequestParam String firm,
            @RequestParam String model,
            @RequestParam String price,
            @RequestParam String type,
            ModelMap modelMap) {
        Product product = new Product(firm, model, Double.valueOf(price), type);
        productService.updateProduct(currentProduct.getId(), product);
        String s = product.toString() + " - updated";
        modelMap.put("message", s);
        modelMap.put("back", "/Admin/adminProductMenu.jsp");
        currentProduct = null;
        return "/Admin/showProduct";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(
            @RequestParam String id,
            ModelMap modelMap) {
        long idL = Long.valueOf(id);
        String s = productService.getByID(idL).toString() + " - deleted";
        productService.deleteById(idL);
        modelMap.put("message", s);
        modelMap.put("back", "/Admin/adminProductMenu.jsp");
        return "/Admin/showProduct";
    }
}
