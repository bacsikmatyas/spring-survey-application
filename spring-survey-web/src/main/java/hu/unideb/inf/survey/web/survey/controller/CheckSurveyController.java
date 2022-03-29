package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.survey.model.SurveyDataDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyDataDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckSurveyController {

    private static final String REQUEST_MAPPING = "/checkSurvey";

    private final SurveyService surveyService;
    private final SurveyDataDtoTransformer surveyDataDtoTransformer;

    @Autowired
    public CheckSurveyController(SurveyService surveyService, SurveyDataDtoTransformer surveyDataDtoTransformer) {
        this.surveyService = surveyService;
        this.surveyDataDtoTransformer = surveyDataDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String checkSurveyController(){
        return "checkSurvey";
    }

    @ModelAttribute("surveyData")
    public SurveyDataDto generateSurveyData(@RequestParam(name = "id") long surveyId){
        SurveyDomain surveyDomain = surveyService.findSurveyById(surveyId);
        return surveyDataDtoTransformer.from(surveyDomain);
    }
}
