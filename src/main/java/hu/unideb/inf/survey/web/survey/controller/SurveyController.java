package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyQuestionDomainTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController {
    private static final String REQUEST_MAPPING = "/survey";

    private final SurveyQuestionService surveyQuestionService;

    private final SurveyQuestionDomainTransformer surveyQuestionDomainTransformer;

    @Autowired
    public SurveyController(SurveyQuestionService surveyQuestionService, SurveyQuestionDomainTransformer surveyQuestionDomainTransformer) {
        this.surveyQuestionService = surveyQuestionService;
        this.surveyQuestionDomainTransformer = surveyQuestionDomainTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String surveyController(@RequestParam(name = "id") long id){


        return "survey";
    }

    @ModelAttribute("questions")
    public List<QuestionDto> questions(@RequestParam(name = "id") long id){
        List<SurveyQuestionDomain> questionsBySurveyId = surveyQuestionService.findSurveyQuestionsBySurveyId(id);

        List<QuestionDto> questionDtos = new ArrayList<>();

        for (SurveyQuestionDomain surveyQuestionDomain: questionsBySurveyId){
            QuestionDto tmp = surveyQuestionDomainTransformer.from(surveyQuestionDomain);
            questionDtos.add(tmp);
        }

        return questionDtos;
    }
}