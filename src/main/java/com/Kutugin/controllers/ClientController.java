package com.Kutugin.controllers;

import com.Kutugin.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
//    @RequestParam(name="name", required=false, defaultValue="World") String name
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "adminMenu";
    }

    @RequestMapping(value = "/clientss", method = RequestMethod.GET)
    public String showClients(ModelMap modelMap){
        System.out.println("YoYo");
        modelMap.put("message",clientService.getAllClients());
        return "clients";
    }
}
