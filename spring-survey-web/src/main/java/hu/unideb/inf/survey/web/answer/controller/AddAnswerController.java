package hu.unideb.inf.survey.web.answer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddAnswerController {
    private static final String REQUEST_MAPPING = "/addAnswer";

    @GetMapping(REQUEST_MAPPING)
    public String addAnswerController() {
        return "addAnswer";
    }

    @ModelAttribute("questionId")
    public Long getQuestionId(@RequestParam(name = "questionId") Long questionId) {
        return questionId;
    }
}
