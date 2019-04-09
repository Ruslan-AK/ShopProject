package com.Kutugin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.Kutugin.controllers.States.*;

@Controller
public class MainController {

    @RequestMapping(value = "/enterAsAdmin", method = RequestMethod.POST)
    public String enterAsAdmin() {
        return "/Admin/adminMenu";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String enter() {
        return "index";
    }

    @RequestMapping(value = "/adminProductMenu", method = RequestMethod.POST)
    public String adminProductMenu() {
        return "/Admin/adminProductMenu";
    }

    @RequestMapping(value = "/adminClientMenu", method = RequestMethod.POST)
    public String adminClientMenu() {
        return "/Admin/adminClientMenu";
    }

    @RequestMapping(value = "/adminDeleteClientEnterPhone", method = RequestMethod.POST)
    public String adminDeleteClientEnterPhone() {
        return "/Admin/adminDeleteClientEnterPhone";
    }

    @RequestMapping(value = "/enterProductIDtoUpdate", method = RequestMethod.POST)
    public String enterProductIDtoUpdate() {
        return "/Admin/enterProductIDtoUpdate";
    }

    @RequestMapping(value = "/adminOrderMenu", method = RequestMethod.POST)
    public String adminOrderMenu() {
        return "/Admin/adminOrderMenu";
    }

    @RequestMapping(value = "/adminDeleteOrder", method = RequestMethod.POST)
    public String adminDeleteOrder() {
        return "/Admin/enterOrderIDtoDelete";
    }

    @RequestMapping(value = "/enterProductIDtoDelete", method = RequestMethod.POST)
    public String enterProductIDtoDelete() {
        return "/Admin/enterProductIDtoDelete";
    }

    @RequestMapping(value = "/adminCreateClient", method = RequestMethod.POST)
    public String adminCreateClient() {
        return "/Admin/createClient";
    }

    @RequestMapping(value = "/adminClientLoginMenu", method = RequestMethod.POST)
    public String adminClientLoginMenu() {
        return "/Admin/adminClientLoginMenu";
    }

    @RequestMapping(value = "/myAccount", method = RequestMethod.POST)
    public String myAccount() {
        return "/Client/myAccount";
    }

    @RequestMapping(value = "/enterAsClient", method = RequestMethod.POST)
    public String enterAsClient() {
        if (isSignIn()) {
            return ("/Client/clientMenu");
        } else {
            return ("/Client/clientLoginMenu");
        }
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public String logOut() {
        setSignIn(false);
        setCurrentClient(null);
        setCurrentOrder(null);
        return "index";
    }
}
