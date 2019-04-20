package com.Kutugin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.Kutugin.controllers.States.*;

@Controller
public class MainController {

    @RequestMapping(value = "/enterAsAdmin", method = RequestMethod.GET)
    public String enterAsAdmin() {
        return "/Admin/adminMenu";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String enter() {
        return "index";
    }

    @RequestMapping(value = "/adminProductMenu", method = RequestMethod.GET)
    public String adminProductMenu() {
        return "/Admin/adminProductMenu";
    }

    @RequestMapping(value = "/adminClientMenu", method = RequestMethod.GET)
    public String adminClientMenu() {
        return "/Admin/adminClientMenu";
    }

    @RequestMapping(value = "/adminDeleteClientEnterPhone", method = RequestMethod.GET)
    public String adminDeleteClientEnterPhone() {
        return "/Admin/adminDeleteClientEnterPhone";
    }

    @RequestMapping(value = "/enterProductIDtoUpdate", method = RequestMethod.GET)
    public String enterProductIDtoUpdate() {
        return "/Admin/enterProductIDtoUpdate";
    }

    @RequestMapping(value = "/adminOrderMenu", method = RequestMethod.GET)
    public String adminOrderMenu() {
        return "/Admin/adminOrderMenu";
    }

    @RequestMapping(value = "/adminDeleteOrder", method = RequestMethod.GET)
    public String adminDeleteOrder() {
        return "/Admin/enterOrderIDtoDelete";
    }

    @RequestMapping(value = "/enterProductIDtoDelete", method = RequestMethod.GET)
    public String enterProductIDtoDelete() {
        return "/Admin/enterProductIDtoDelete";
    }

    @RequestMapping(value = "/adminCreateClient", method = RequestMethod.GET)
    public String adminCreateClient() {
        return "/Admin/createClient";
    }

    @RequestMapping(value = "/adminClientLoginMenu", method = RequestMethod.GET)
    public String adminClientLoginMenu() {
        return "/Admin/adminClientLoginMenu";
    }

    @RequestMapping(value = "/myAccount", method = RequestMethod.GET)
    public String myAccount() {
        return "/Client/myAccount";
    }

    @RequestMapping(value = "/enterAsClient", method = RequestMethod.GET)
    public String enterAsClient() {
        if (isSignIn()) {
            return ("/Client/clientMenu");
        } else {
            return ("/Client/clientLoginMenu");
        }
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut() {
        setSignIn(false);
        setCurrentClient(null);
        setCurrentOrder(null);
        return "index";
    }
}
