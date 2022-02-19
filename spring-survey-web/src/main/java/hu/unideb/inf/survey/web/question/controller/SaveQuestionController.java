package hu.unideb.inf.survey.web.question.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SaveQuestionController {
    private static final String REQUEST_MAPPING = "/saveQuestion";

    private final SurveyQuestionService surveyQuestionService;

    @Autowired
    public SaveQuestionController(SurveyQuestionService surveyQuestionService) {
        this.surveyQuestionService = surveyQuestionService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveQuestionController(HttpServletRequest request,
                                         @ModelAttribute(name = "questionId") Long questionId,
                                         @ModelAttribute(name = "questionText") String questionText,
                                         @ModelAttribute(name = "multiselect") boolean multiselect) {
        surveyQuestionService.editQuestionText(questionId, questionText, multiselect);
        String referer = request.getHeader("Referer");
        String successParam = "&successfulSubmit";
        if (referer.contains(successParam)) {
            return "redirect:" + referer;
        } else {
            return "redirect:" + referer + successParam;
        }
    }
}
