package hu.unideb.inf.survey.web.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddSurveyController {
    private static final String REQUEST_MAPPING = "/addSurvey";

    @GetMapping(REQUEST_MAPPING)
    public String addSurveyController(){
        return "addSurvey";
    }
}
