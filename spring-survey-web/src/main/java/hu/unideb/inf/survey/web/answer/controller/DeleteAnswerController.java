package hu.unideb.inf.survey.web.answer.controller;

import hu.unideb.inf.survey.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteAnswerController {
    private static final String REQUEST_MAPPING = "/deleteAnswer";

    private final QuestionAnswerService questionAnswerService;

    @Autowired
    public DeleteAnswerController(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String deleteAnswerController(HttpServletRequest request,
                                         @RequestParam Long id) {
        questionAnswerService.deleteQuestionAnswerById(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
