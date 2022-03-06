package hu.unideb.inf.survey.web.question.controller;

import hu.unideb.inf.survey.service.QuestionAnswerService;
import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
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

    private final QuestionAnswerService questionAnswerService;

    @Autowired
    public SaveNewQuestionController(SurveyQuestionService surveyQuestionService, QuestionAnswerService questionAnswerService) {
        this.surveyQuestionService = surveyQuestionService;
        this.questionAnswerService = questionAnswerService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveNewQuestionController(@ModelAttribute(name = "surveyId") Long surveyId,
                                            @ModelAttribute(name = "questionText") String questionText,
                                            @ModelAttribute(name = "multiselect") boolean multiselect,
                                            @ModelAttribute(name = "freetext") boolean freetext) {
        SurveyQuestionDomain surveyQuestionDomain = new SurveyQuestionDomain();
        surveyQuestionDomain.setQuestionText(questionText);
        surveyQuestionDomain.setMultiselect(multiselect);
        surveyQuestionDomain.setFreetext(freetext);
        SurveyDomain surveyDomain = new SurveyDomain();
        surveyDomain.setId(surveyId);
        surveyQuestionDomain.setSurvey(surveyDomain);
        Long questionId = surveyQuestionService.saveNewQuestion(surveyQuestionDomain);
        if (freetext){
            QuestionAnswerDomain questionAnswerDomain = new QuestionAnswerDomain();
            questionAnswerDomain.setAnswerText("This is a free text question.");
            SurveyQuestionDomain surveyQuestionDomainTmp = new SurveyQuestionDomain();
            surveyQuestionDomainTmp.setId(questionId);
            questionAnswerDomain.setQuestion(surveyQuestionDomainTmp);
            questionAnswerService.saveNewAnswer(questionAnswerDomain);
        }
        String redirectUrl = String.format("/editSurvey?id=%d", surveyId);
        return "redirect:" + redirectUrl;
    }
}
