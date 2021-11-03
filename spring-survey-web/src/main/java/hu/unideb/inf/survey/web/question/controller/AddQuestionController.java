package hu.unideb.inf.survey.web.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddQuestionController {
    private static final String REQUEST_MAPPING = "/addQuestion";

    @RequestMapping(REQUEST_MAPPING)
    public String addQuestionController() {
        return "addQuestion";
    }

    @ModelAttribute("surveyId")
    public Long getSurveyId(@RequestParam(name = "surveyId") Long surveyId) {
        return surveyId;
    }
}
