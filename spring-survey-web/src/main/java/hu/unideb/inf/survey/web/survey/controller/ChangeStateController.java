package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangeStateController {
    private static final String REQUEST_MAPPING = "/changeSurveyState";

    private final SurveyService surveyService;

    @Autowired
    public ChangeStateController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping(REQUEST_MAPPING)
    public String changeStateController(HttpServletRequest request,
                                        @RequestParam(name = "surveyId") Long surveyId) {
        surveyService.changeSurveyState(surveyId);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
