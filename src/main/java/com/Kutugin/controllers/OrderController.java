package com.Kutugin.controllers;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import com.Kutugin.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.Kutugin.controllers.States.*;

@Controller
public class OrderController {

    private ClientService clientService;
    private OrderService orderService;
    private ProductService productService;

    @Autowired
    public OrderController(ClientService clientService, OrderService orderService, ProductService productService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.DELETE)
    public String deleteOrder(
            @RequestParam String id,
            ModelMap modelMap) {
        long idL = Long.valueOf(id);
        String s = orderService.getByID(idL).toString() + " - deleted";
        orderService.deleteById(idL);
        modelMap.put("title", "Order deleted");
        modelMap.put("message", s);
        return "/showItem";
    }


    @RequestMapping(value = "/myOrder", method = RequestMethod.GET)
    public String myOrder(ModelMap modelMap) {
        if (getCurrentOrder() == null) {
            modelMap.put("currentOrder", "Order empty");
            modelMap.put("summaryPrice", "0");
        } else {
            modelMap.put("currentOrder", getCurrentOrder().toString());
            modelMap.put("summaryPrice", orderService.summaryPrice(getCurrentOrder()));
        }
        return "/Client/clientOrder";
    }

    @RequestMapping(value = "/showOrdersByClient", method = RequestMethod.GET)
    public String showOrdersByClient(ModelMap modelMap) {
        StringBuilder sb = new StringBuilder();
        for (Order o : orderService.getOrdersByClient(getCurrentClient().getId())) {
            sb.append("<fieldset>");
            sb.append(o.toString());
            sb.append("</fieldset>");
        }
        String s = sb.toString();
        modelMap.put("message", "".equals(s) ? "no orders yet" : s);
        modelMap.put("title", "Show orders");
        return "/showItem";
    }

    @RequestMapping(value = "/showOrders", method = RequestMethod.GET)
    public String showOrders(ModelMap modelMap) {
        StringBuilder sb = new StringBuilder();
        for (Client c : clientService.getAllClients()) {
            if (orderService.getOrdersByClient(c.getId()).size() > 0) {
                sb.append("<fieldset>");
                sb.append("<fieldset>");
                sb.append(c.toString() + "\n");
                sb.append("</fieldset>");
                for (Order order : orderService.getOrdersByClient(c.getId())) {
                    sb.append("<fieldset>");
                    sb.append(order + "\n");
                    sb.append("</fieldset>");
                }
                sb.append("</fieldset>");
            }
            modelMap.put("message", sb.toString());
            modelMap.put("title", "Show orders");
        }
        return "/showItem";
    }


    @RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
    public String buyProduct(@RequestParam String id, ModelMap modelMap) {
        long idL = Long.parseLong(id);
        if (getCurrentOrder() == null) {
            setCurrentOrder(new Order());
            System.out.println("currentOrder.getId INIT " + getCurrentOrder().getId());
            orderService.addClientOrder(getCurrentClient(), getCurrentOrder());
        }
        Product goods = productService.getByID(idL);
        getCurrentOrder().addProduct(goods);
        orderService.update(getCurrentOrder().getId(), getCurrentOrder());
        modelMap.put("message", "<h2>You buy " + goods.getFirm() + " " + goods.getModel() + "</h2>\n");
        modelMap.put("title", "Buy product");
        return "/showItem";
    }
}