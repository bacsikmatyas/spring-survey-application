package hu.unideb.inf.survey.web.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddSurveyController {
    private static final String REQUEST_MAPPING = "/addSurvey";

    @RequestMapping(REQUEST_MAPPING)
    public String addSurveyController(){
        return "addSurvey";
    }
}
