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

import static com.Kutugin.controllers.States.getCurrentProduct;
import static com.Kutugin.controllers.States.setCurrentProduct;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/showProducts", method = RequestMethod.POST)
    public String showProducts(ModelMap modelMap) {
        String productsString = "";
        if (productService.getProducts().size() > 0) {
            for (Product product : productService.getProducts()) {
                productsString += "<fieldset>" + product.toString() + "</fieldset>";
            }
        } else {
            productsString = "No products";
        }
        modelMap.put("title", "Show products");
        modelMap.put("message", productsString);
        return "/showItem";
    }

    @RequestMapping(value = "/createProductBlank", method = RequestMethod.POST)
    public String createProduct(ModelMap modelMap) {
        String productsTypesString = "";
        for (ProductType pt : ProductType.values()) {
            if (getCurrentProduct() != null && pt.toString().equals(getCurrentProduct().getType())) {
                productsTypesString += "<option value=\"" + pt.toString() + "\" selected>" + pt.toString() + "</option>\n";
            } else {
                productsTypesString += "<option value=\"" + pt.toString() + "\">" + pt.toString() + "</option>\n";
            }
        }
        modelMap.put("options", productsTypesString);
        return "/Admin/createProduct";
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String createProduct(
            @RequestParam String firm,
            @RequestParam String model,
            @RequestParam String price,
            @RequestParam String type,
            ModelMap modelMap) {
        Product product = new Product(firm, model, Double.valueOf(price), type);
        long id = productService.saveProduct(product);
        String s = productService.getByID(id).toString() + " - created";
        modelMap.put("message", s);
        modelMap.put("title", "Product created");
        return "/showItem";
    }

    @RequestMapping(value = "/updateProductBlank", method = RequestMethod.POST)
    public String updateProductBlank(
            @RequestParam String id,
            ModelMap modelMap) {
        if (productService.getByID(Long.parseLong(id)) != null) {
            setCurrentProduct(productService.getByID(Long.parseLong(id)));
            String productsTypesString = "";
            for (ProductType pt : ProductType.values()) {
                if (pt.toString().equals(getCurrentProduct().getType())) {
                    productsTypesString += "<option value=\"" + pt.toString() + "\" selected>" + pt.toString() + "</option>\n";
                } else {
                    productsTypesString += "<option value=\"" + pt.toString() + "\">" + pt.toString() + "</option>\n";
                }
            }
            modelMap.put("options", productsTypesString);
            modelMap.put("price", getCurrentProduct().getPrice());
            modelMap.put("model", getCurrentProduct().getModel());
            modelMap.put("firm", getCurrentProduct().getFirm());
            return "/Admin/updateProductForm";
        } else {
            modelMap.put("message", "Product not found");
            modelMap.put("title", "Product not found");
            return "/showItem";
        }

    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(
            @RequestParam String firm,
            @RequestParam String model,
            @RequestParam String price,
            @RequestParam String type,
            ModelMap modelMap) {
        Product product = new Product(firm, model, Double.valueOf(price), type);
        productService.updateProduct(getCurrentProduct().getId(), product);
        String s = product.toString() + " - updated";
        setCurrentProduct(null);
        modelMap.put("message", s);
        modelMap.put("title", "Product updated");
        return "/showItem";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public String deleteProduct(
            @RequestParam String id,
            ModelMap modelMap) {
        long idL = Long.valueOf(id);
        String s = productService.getByID(idL).toString() + " - deleted";
        productService.deleteById(idL);
        modelMap.put("message", s);
        modelMap.put("title", "Product deleted");
        return "/showItem";
    }

    @RequestMapping(value = "/showBuyProducts", method = RequestMethod.POST)
    public String showBuyProducts(ModelMap modelMap) {
        String productsString = "";
        if (productService.getProducts().size() > 0) {
            for (Product product : productService.getProducts()) {
                productsString += "<p>" + product.toString() + "</p>";
            }
        } else {
            productsString = "No products";
        }
        modelMap.put("message", productsString);
        return "/Client/showBuyProducts";
    }
}