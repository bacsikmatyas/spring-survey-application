package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.survey.model.MySurveyDto;
import hu.unideb.inf.survey.web.survey.transformer.MySurveyTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MySurveysController {
    private static final String REQUEST_MAPPING = "/mySurveys";

    private final SurveyService surveyService;

    private final MySurveyTransformer mySurveyTransformer;

    @Autowired
    public MySurveysController(SurveyService surveyService, MySurveyTransformer mySurveyTransformer) {
        this.surveyService = surveyService;
        this.mySurveyTransformer = mySurveyTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String mySurveyController(){
        return "mySurveys";
    }

    @ModelAttribute("mySurveys")
    public List<MySurveyDto> generateMySurveys(@CookieValue("currentUserId") String currentUserIdString){
        long currentUserId = Long.parseLong(currentUserIdString);
        List<SurveyDomain> userSurveys = surveyService.findUserSurveys(currentUserId);

        List<MySurveyDto> userSurveyDtos = new ArrayList<>();
        for (SurveyDomain surveyDomain: userSurveys){
            MySurveyDto tmp = mySurveyTransformer.from(surveyDomain);
            userSurveyDtos.add(tmp);
        }

        return userSurveyDtos;
    }

}
