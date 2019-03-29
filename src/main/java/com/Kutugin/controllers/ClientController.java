package com.Kutugin.controllers;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.services.ClientService;
import com.Kutugin.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;
    private boolean signIn = false;
    private Client adminCurrentClient = null;
    private Order currentOrder = null;
    private Client currentClient = null;

    @RequestMapping(value = "/enterAsAdmin", method = RequestMethod.POST)
    public String start() {
        System.out.println("WORK!");
        return "/Admin/adminMenu";
    }

    @RequestMapping(value = "/enterAsClient", method = RequestMethod.POST)
    public String enterAsClient() {
        if (signIn) {
            return ("/Client/clientMenu");
        } else {
            return ("/Client/clientLoginMenu");
        }
    }

    @RequestMapping(value = "/myOrder", method = RequestMethod.POST)
    public String myOrder(ModelMap modelMap) {
        if (currentOrder == null) {
            return "/Client/clientOrderEmpty";
        }
        modelMap.put("currentOrder", currentOrder.toString());
        modelMap.put("summaryPrice", orderService.summaryPrice(currentOrder));
        return "/Client/clientOrder";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public String logOut() {
        signIn = false;
        currentClient = null;
        currentOrder = null;
        return "mainMenu";
    }

    @RequestMapping(value = "/showOrdersByClient", method = RequestMethod.POST)
    public String showOrdersByClient(ModelMap modelMap) {
        StringBuilder sb = new StringBuilder();
        for (Order o : orderService.getOrdersByClient(currentClient.getId())) {
            sb.append(o.toString());
            sb.append("\n");
        }
        String s = sb.toString();
        modelMap.put("ordersByClient", s.equals("") ? "no orders yet" : s);
        return "/Client/showOrdersByClient";
    }

    @RequestMapping(value = "/myAccountInfo", method = RequestMethod.POST)
    public String myAccountInfo(ModelMap modelMap) {
        modelMap.put("clientString", currentClient.toString());
        modelMap.put("back", "/Client/myAccount.jsp");
        return "/Client/showClient";
    }

    @RequestMapping(value = "/deleteCurrentClient", method = RequestMethod.POST)
    public String deleteCurrentClient(ModelMap modelMap) {
        String s = currentClient.toString() + " - Deleted";
        logOut();
        modelMap.put("clientString", s);
        modelMap.put("back", "/mainMenu.jsp");
        clientService.deleteClient(currentClient.getId());
        return "/Client/showClient";
    }

    @RequestMapping(value = "/clientLogin", method = RequestMethod.POST)
    public String clientLogin(@RequestParam String phone, ModelMap modelMap) {
        if (signIn) {
            return "/Client/clientMenu";
        } else {
            if (clientService.isPresent(phone)) {
                currentClient = clientService.getClientByID(clientService.getIDByPhoneNumber(phone));
                signIn = true;
                return "/Client/clientMenu";
            } else {
                modelMap.put("clientString", "Client not found");
                modelMap.put("back", "/mainMenu.jsp");
                return "/Client/showClient";
            }
        }
    }

    @RequestMapping(value = "/showClients", method = RequestMethod.GET)
    public String showClients(ModelMap modelMap) {
        String clientsString = "";
        if (clientService.getAllClients().size() > 0) {
            for (Client client : clientService.getAllClients()) {
                clientsString += "<p>" + client.toString() + "</p>";
            }
        } else {
            clientsString = "No clients";
        }
        modelMap.put("clientsString", clientsString);
        return "/Admin/showClients";
    }

    @RequestMapping(value = "/updateCurrentClientBlank", method = RequestMethod.POST)
    public String updateCurrentClientBlank(ModelMap modelMap) {
        modelMap.put("name", currentClient.getName());
        modelMap.put("surname", currentClient.getSurname());
        modelMap.put("age", currentClient.getAge());
        modelMap.put("email", currentClient.getEmail());
        return "/Client/updateCurrentClientForm";
    }

    @RequestMapping(value = "/updateCurrentClient", method = RequestMethod.POST)
    public String updateCurrentClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String email,
            ModelMap modelMap) {
        clientService.updateClient(currentClient.getId(), new Client(currentClient.getId(), name, surname, age, email, null));
        currentClient = clientService.getClientByID(currentClient.getId());
        String s = currentClient.toString() + " - updated";
        modelMap.put("clientString", s);
        modelMap.put("back", "/Client/myAccount.jsp");
        return "/Client/showClient";
    }

    @RequestMapping(value = "/createClient", method = RequestMethod.POST)
    public String createClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String phone,
            @RequestParam String email,
            ModelMap modelMap) {
        clientService.createClient(name, surname, age, email, phone);
        String s = clientService.getClientByID(clientService.getIDByPhoneNumber(phone)).toString() + " - created";
        modelMap.put("clientString", s);
        modelMap.put("back", "/Admin/adminClientMenu.jsp");
        return "/Client/showClient";
    }

    @RequestMapping(value = "/adminClientLogin", method = RequestMethod.POST)
    public String adminClientLogin(
            @RequestParam String phone,
            ModelMap modelMap) {
        if (clientService.isPresent(phone)) {
            modelMap.put("name", adminCurrentClient.getName());
            modelMap.put("surname", adminCurrentClient.getSurname());
            modelMap.put("age", adminCurrentClient.getAge());
            modelMap.put("email", adminCurrentClient.getEmail());
            modelMap.put("phone", adminCurrentClient.getPhoneNumber());
            adminCurrentClient = clientService.getClientByID(clientService.getIDByPhoneNumber(phone));
            return "/Admin/updateAdminCurrentClientForm";
        } else {
            modelMap.put("clientString", "client not found");
            modelMap.put("back", "/Admin/adminClientMenu.jsp");
            return "/Client/showClient";
        }
    }

//    @RequestMapping(value = "/updateAdminCurrentClientBlank", method = RequestMethod.POST)
//    public String updateAdminCurrentClientBlank(ModelMap modelMap) {
//        modelMap.put("name", adminCurrentClient.getName());
//        modelMap.put("surname", adminCurrentClient.getSurname());
//        modelMap.put("age", adminCurrentClient.getAge());
//        modelMap.put("email", adminCurrentClient.getEmail());
//        modelMap.put("phone", adminCurrentClient.getPhoneNumber());
//        return "/Admin/updateAdminCurrentClientForm";
//    }

    @RequestMapping(value = "/updateAdminCurrentClient", method = RequestMethod.POST)
    public String updateAdminCurrentClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String email,
            @RequestParam String phone,
            ModelMap modelMap) {
        clientService.updateClient(adminCurrentClient.getId(), new Client(adminCurrentClient.getId(), name, surname, age, email, phone));
        adminCurrentClient = clientService.getClientByID(adminCurrentClient.getId());
        String s = adminCurrentClient.toString() + " - updated";
        adminCurrentClient = null;
        modelMap.put("clientString", s);
        modelMap.put("back", "/Admin/adminClientMenu.jsp");
        return "/Client/showClient";
    }

    @RequestMapping(value = "/adminClientDelete", method = RequestMethod.POST)
    public String adminClientDelete(
            @RequestParam String phone,
            ModelMap modelMap) {
        clientService.deleteClient(clientService.getIDByPhoneNumber(phone));
        String s = clientService.getClientByID(clientService.getIDByPhoneNumber(phone)).toString() + " - deleted";
        modelMap.put("clientString", s);
        modelMap.put("back", "/Admin/adminClientMenu.jsp");
        return "/Client/showClient";
    }
}
