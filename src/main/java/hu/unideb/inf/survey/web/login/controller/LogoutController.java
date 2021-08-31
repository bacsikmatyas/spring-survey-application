package hu.unideb.inf.survey.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    private static final String REQUEST_MAPPING = "/logout";

    @GetMapping(REQUEST_MAPPING)
    String logoutController(){
        return "redirect:login?logout=true";
    }
}
