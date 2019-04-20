package com.Kutugin.controllers;

import com.Kutugin.domain.Client;
import com.Kutugin.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.Kutugin.controllers.States.*;

@Controller
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String clients(ModelMap modelMap) {
        String clientsString = "";
        if (clientService.getAllClients().size() > 0) {
            for (Client client : clientService.getAllClients()) {
                clientsString += "<fieldset>" + client.toString() + "</fieldset>";
            }
        } else {
            clientsString = "No clients";
        }
        modelMap.put("message", clientsString);
        modelMap.put("title", "Clients");
        return "/showItem";
    }

    @RequestMapping(value = "/getCurrentClientBlank", method = RequestMethod.GET)
    public String getCurrentClientBlank(ModelMap modelMap) {
        modelMap.put("name", getCurrentClient().getName());
        modelMap.put("surname", getCurrentClient().getSurname());
        modelMap.put("age", getCurrentClient().getAge());
        modelMap.put("email", getCurrentClient().getEmail());
        return "/Client/updateCurrentClientForm";
    }

    @RequestMapping(value = "/updateCurrentClient", method = RequestMethod.PUT)
    public String updateCurrentClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String email,
            ModelMap modelMap) {
        clientService.updateClient(getCurrentClient().getId(), new Client(getCurrentClient().getId(), name, surname, age, email, null));
        setCurrentClient(clientService.getClientByID(getCurrentClient().getId()));
        String s = getCurrentClient().toString() + " - updated";
        modelMap.put("message", s);
        modelMap.put("title", "Client updated");
        return "/showItem";
    }
//
//    @RequestMapping(value = "/adminUpdateCurrentClient", method = RequestMethod.PUT)
//    public String adminUpdateCurrentClient(
//            @RequestParam String name,
//            @RequestParam String surname,
//            @RequestParam String age,
//            @RequestParam String email,
//            @RequestParam String phone,
//            ModelMap modelMap) {
//        clientService.updateClient(getAdminCurrentClient().getId(), new Client(getAdminCurrentClient().getId(), name, surname, age, email, phone));
//        setAdminCurrentClient(clientService.getClientByID(getAdminCurrentClient().getId()));
//        String s = getAdminCurrentClient().toString() + " - updated";
//        setAdminCurrentClient(null);
//        modelMap.put("message", s);
//        modelMap.put("title", "Client updated");
//        return "/showItem";
//    }

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
        modelMap.put("message", s);
        modelMap.put("title", "Client created");
        return "/showItem";
    }

    @RequestMapping(value = "/adminClientLogin", method = RequestMethod.GET)
    public String adminClientLogin(
            @RequestParam String phone,
            ModelMap modelMap) {
        if (clientService.isPresent(phone)) {
            setAdminCurrentClient(clientService.getClientByID(clientService.getIDByPhoneNumber(phone)));
            modelMap.put("name", getAdminCurrentClient().getName());
            modelMap.put("surname", getAdminCurrentClient().getSurname());
            modelMap.put("age", getAdminCurrentClient().getAge());
            modelMap.put("email", getAdminCurrentClient().getEmail());
            modelMap.put("phone", getAdminCurrentClient().getPhoneNumber());
            return "/Admin/updateAdminCurrentClientForm";
        } else {
            modelMap.put("title", "Client not found");
            modelMap.put("message", "Client not found");
            return "/showItem";
        }
    }

    @RequestMapping(value = "/updateAdminCurrentClient", method = RequestMethod.PUT)
    public String updateAdminCurrentClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String email,
            @RequestParam String phone,
            ModelMap modelMap) {
        clientService.updateClient(getAdminCurrentClient().getId(), new Client(getAdminCurrentClient().getId(), name, surname, age, email, phone));
        setAdminCurrentClient(clientService.getClientByID(getAdminCurrentClient().getId()));
        String s = getAdminCurrentClient().toString() + " - updated";
        setAdminCurrentClient(null);
        modelMap.put("message", s);
        modelMap.put("title", "Client updated");
        return "/showItem";
    }

    @RequestMapping(value = "/adminClientDelete", method = RequestMethod.DELETE)
    public String adminClientDelete(
            @RequestParam String phone,
            ModelMap modelMap) {
        String s = clientService.getClientByID(clientService.getIDByPhoneNumber(phone)).toString() + " - deleted";
        clientService.deleteClient(clientService.getIDByPhoneNumber(phone));
        modelMap.put("message", s);
        modelMap.put("title", "Client deleted");
        return "/showItem";
    }

    @RequestMapping(value = "/myAccountInfo", method = RequestMethod.GET)
    public String myAccountInfo(ModelMap modelMap) {
        modelMap.put("message", getCurrentClient().toString());
        modelMap.put("title", "Account info");
        return "/showItem";
    }

    @RequestMapping(value = "/clientLogin", method = RequestMethod.GET)
    public String clientLogin(@RequestParam String phone, ModelMap modelMap) {
        if (isSignIn()) {
            return "/Client/clientMenu";
        } else {
            if (clientService.isPresent(phone)) {
                setCurrentClient(clientService.getClientByID(clientService.getIDByPhoneNumber(phone)));
                setSignIn(true);
                return "/Client/clientMenu";
            } else {
                modelMap.put("clientString", "Client not found");
                modelMap.put("title", "Error");
                return "/showItem";
            }
        }
    }
}