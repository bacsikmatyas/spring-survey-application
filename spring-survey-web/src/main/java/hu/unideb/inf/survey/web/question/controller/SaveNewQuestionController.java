package hu.unideb.inf.survey.web.question.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaveNewQuestionController {
    private static final String REQUEST_MAPPING = "/saveNewQuestion";

    private final SurveyQuestionService surveyQuestionService;

    @Autowired
    public SaveNewQuestionController(SurveyQuestionService surveyQuestionService) {
        this.surveyQuestionService = surveyQuestionService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveNewQuestionController(@ModelAttribute(name = "surveyId") Long surveyId,
                                            @ModelAttribute(name = "questionText") String questionText) {
        SurveyQuestionDomain surveyQuestionDomain = new SurveyQuestionDomain();
        surveyQuestionDomain.setQuestionText(questionText);
        SurveyDomain surveyDomain = new SurveyDomain();
        surveyDomain.setId(surveyId);
        surveyQuestionDomain.setSurvey(surveyDomain);
        surveyQuestionService.saveNewQuestion(surveyQuestionDomain);
        String redirectUrl = String.format("/editSurvey?id=%d", surveyId);
        return "redirect:" + redirectUrl;
    }
}
