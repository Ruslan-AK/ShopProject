//package com.Kutugin.controllers;
//
//import com.Kutugin.services.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class ProductController {
//    @Autowired
//    private ClientService clientService;
//    @RequestMapping(value = "clientss", method = RequestMethod.GET)
//    public String showClients(ModelMap modelMap){
//        modelMap.put("message",clientService.getAllClients());
//
//        return "clients";
//    }
//}
