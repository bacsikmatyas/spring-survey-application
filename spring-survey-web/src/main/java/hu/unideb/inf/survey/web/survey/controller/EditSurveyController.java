package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyQuestionDtoTransformer;
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
    private final SurveyQuestionDtoTransformer surveyQuestionDtoTransformer;

    @Autowired
    public EditSurveyController(SurveyQuestionService surveyQuestionService, SurveyQuestionDtoTransformer surveyQuestionDtoTransformer) {
        this.surveyQuestionService = surveyQuestionService;
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
}
