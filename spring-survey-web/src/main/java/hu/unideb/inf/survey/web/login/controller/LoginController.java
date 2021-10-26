package hu.unideb.inf.survey.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private static final String REQUEST_MAPPING = "/login";

    @GetMapping(REQUEST_MAPPING)
    public String loginController(){
        return "login";
    }
}
