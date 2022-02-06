package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.SurveyService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.question.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.model.SurveyDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyDtoTransformer;
import hu.unideb.inf.survey.web.question.transformer.SurveyQuestionDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EditSurveyController {
    private static final String REQUEST_MAPPING = "/editSurvey";

    private final SurveyQuestionService surveyQuestionService;
    private final SurveyService surveyService;
    private final SurveyDtoTransformer surveyDtoTransformer;
    private final SurveyQuestionDtoTransformer surveyQuestionDtoTransformer;

    @Autowired
    public EditSurveyController(SurveyQuestionService surveyQuestionService, SurveyService surveyService, SurveyDtoTransformer surveyDtoTransformer, SurveyQuestionDtoTransformer surveyQuestionDtoTransformer) {
        this.surveyQuestionService = surveyQuestionService;
        this.surveyService = surveyService;
        this.surveyDtoTransformer = surveyDtoTransformer;
        this.surveyQuestionDtoTransformer = surveyQuestionDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String editSurveyController(){
        return "editSurvey";
    }

    @ModelAttribute("questions")
    public List<QuestionDto> generateQuestions(@RequestParam(name = "id") long id){
        List<SurveyQuestionDomain> surveyQuestionsBySurveyId = surveyQuestionService.findSurveyQuestionsBySurveyId(id);
        return surveyQuestionDtoTransformer.from(surveyQuestionsBySurveyId);
    }

    @ModelAttribute("survey")
    public SurveyDto generateSurvey(@RequestParam(name = "id") long id){
        SurveyDomain survey = surveyService.findSurveyById(id);
        return surveyDtoTransformer.from(survey);
    }
}
