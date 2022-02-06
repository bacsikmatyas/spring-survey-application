package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.survey.model.SurveyDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {
    private static final String REQUEST_MAPPING = "/survey";

    private final SurveyService surveyService;

    private final SurveyDtoTransformer surveyDtoTransformer;

    @Autowired
    public SurveyController(SurveyService surveyService, SurveyDtoTransformer surveyDtoTransformer) {
        this.surveyService = surveyService;
        this.surveyDtoTransformer = surveyDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String surveyController() {
        return "survey";
    }

    @ModelAttribute("survey")
    public SurveyDto generateSurvey(@RequestParam(name = "id") long id){
        SurveyDomain survey = surveyService.findSurveyById(id);
        return surveyDtoTransformer.from(survey);
    }
}
