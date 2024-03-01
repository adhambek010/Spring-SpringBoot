package com.springboot.myfirstwebapplication.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService service;

    public LoginController(AuthenticationService service) {
        this.service = service;
    }

    // login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goWelcomePage(@RequestParam String name,
                                @RequestParam String password,
                                ModelMap model) {
        if(service.authenticate(name, password)){
            model.put("name", name);

            // Authentication
            // name - Adham
            // password - 2301
            return "welcome";
        }else {
            model.put("errorMessage", "Invalid Credentials! Please try again");
            return "login";
        }
    }
}
