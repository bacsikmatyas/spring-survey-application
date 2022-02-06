package hu.unideb.inf.survey.web.question.controller;

import hu.unideb.inf.survey.service.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteQuestionController {
    private static final String REQUEST_MAPPING = "/deleteQuestion";

    private final SurveyQuestionService surveyQuestionService;

    @Autowired
    public DeleteQuestionController(SurveyQuestionService surveyQuestionService) {
        this.surveyQuestionService = surveyQuestionService;
    }


    @RequestMapping(REQUEST_MAPPING)
    public String deleteQuestionController(HttpServletRequest request,
                                           @RequestParam Long id){
        surveyQuestionService.deleteSurveyQuestionById(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }
}
