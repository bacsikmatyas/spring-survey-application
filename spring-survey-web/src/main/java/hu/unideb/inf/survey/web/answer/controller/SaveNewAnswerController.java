package hu.unideb.inf.survey.web.answer.controller;

import hu.unideb.inf.survey.service.QuestionAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaveNewAnswerController {
    private static final String REQUEST_MAPPING = "/saveNewAnswer";

    private final QuestionAnswerService questionAnswerService;

    @Autowired
    public SaveNewAnswerController(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveNewAnswerController(@ModelAttribute(name = "questionId") Long questionId,
                                          @ModelAttribute(name = "answerText") String answerText) {
        QuestionAnswerDomain questionAnswerDomain = new QuestionAnswerDomain();
        questionAnswerDomain.setAnswerText(answerText);
        SurveyQuestionDomain surveyQuestionDomain = new SurveyQuestionDomain();
        surveyQuestionDomain.setId(questionId);
        questionAnswerDomain.setQuestion(surveyQuestionDomain);
        questionAnswerService.saveNewAnswer(questionAnswerDomain);
        String redirectUrl = String.format("/editQuestion?id=%d", questionId);
        return "redirect:" + redirectUrl;
    }
}
