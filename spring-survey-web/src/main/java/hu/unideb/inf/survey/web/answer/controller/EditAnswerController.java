package hu.unideb.inf.survey.web.answer.controller;

import hu.unideb.inf.survey.service.QuestionAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.web.answer.model.AnswerDto;
import hu.unideb.inf.survey.web.answer.transformer.QuestionAnswerDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditAnswerController {
    private static final String REQUEST_MAPPING = "/editAnswer";

    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerDtoTransformer questionAnswerDtoTransformer;

    @Autowired
    public EditAnswerController(QuestionAnswerService questionAnswerService, QuestionAnswerDtoTransformer questionAnswerDtoTransformer) {
        this.questionAnswerService = questionAnswerService;
        this.questionAnswerDtoTransformer = questionAnswerDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String editAnswerController(){
        return "editAnswer";
    }

    @ModelAttribute("answer")
    public AnswerDto generateAnswer(@RequestParam Long id){
        QuestionAnswerDomain answerDomain = questionAnswerService.findQuestionAnswerById(id);
        return questionAnswerDtoTransformer.from(answerDomain);
    }

    @ModelAttribute("questionId")
    public Long getQuestionId(@RequestParam(name = "id") Long answerId){
        return questionAnswerService.getQuestionIdByAnswerId(answerId);
    }
}
