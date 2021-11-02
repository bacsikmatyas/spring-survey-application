package hu.unideb.inf.survey.web.question.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.transformer.SurveyQuestionDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditQuestionController {
    private static final String REQUEST_MAPPING = "/editQuestion";

    private final SurveyQuestionService surveyQuestionService;
    private final SurveyQuestionDtoTransformer surveyQuestionDtoTransformer;

    @Autowired
    public EditQuestionController(SurveyQuestionService surveyQuestionService, SurveyQuestionDtoTransformer surveyQuestionDtoTransformer) {
        this.surveyQuestionService = surveyQuestionService;
        this.surveyQuestionDtoTransformer = surveyQuestionDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String editQuestionController(){
        return "editQuestion";
    }

    @ModelAttribute("question")
    public QuestionDto generateQuestion(@RequestParam Long id){
        return surveyQuestionDtoTransformer.from(surveyQuestionService.findSurveyQuestionById(id));
    }

    @ModelAttribute("surveyId")
    public Long getSurveyId(@RequestParam(name = "id") Long questionId){
        return surveyQuestionService.getSurveyIdOfQuestion(questionId);
    }
}
