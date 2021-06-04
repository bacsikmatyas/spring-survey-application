package hu.unideb.inf.survey.web.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private static final String REQUEST_MAPPING = "/";

    @GetMapping(REQUEST_MAPPING)
    public String homeController(){
        return "home";
    }
}
