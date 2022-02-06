package hu.unideb.inf.survey.web.answer.controller;

import hu.unideb.inf.survey.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SaveAnswerController {
    private static final String REQUEST_MAPPING = "/saveAnswer";

    private final QuestionAnswerService questionAnswerService;

    @Autowired
    public SaveAnswerController(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @RequestMapping(REQUEST_MAPPING)
    public String saveAnswerController(HttpServletRequest request,
                                       @ModelAttribute(name = "answerId") Long answerId,
                                       @ModelAttribute(name = "answerText") String answerText) {
        questionAnswerService.editAnswerText(answerId, answerText);
        String referer = request.getHeader("Referer");
        String successParam = "&successfulSubmit";
        if (referer.contains(successParam)) {
            return "redirect:" + referer;
        } else {
            return "redirect:" + referer + successParam;
        }
    }
}
