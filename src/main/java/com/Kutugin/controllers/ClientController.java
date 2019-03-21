//package com.Kutugin.controllers;
//
//import com.Kutugin.services.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class ClientController {
//    @Autowired
//    private ClientService clientService;
//    @GetMapping("/clientss")
//    public String showClients(ModelMap modelMap){
//        modelMap.put("message",clientService.getAllClients());
//
//        return "clients";
//    }
//}
