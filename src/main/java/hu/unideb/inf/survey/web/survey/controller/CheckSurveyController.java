package hu.unideb.inf.survey.web.survey.controller;

import hu.unideb.inf.survey.service.SelectedAnswerService;
import hu.unideb.inf.survey.service.SurveyQuestionService;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.model.QuestionStatisticDto;
import hu.unideb.inf.survey.web.survey.model.SurveyDataDto;
import hu.unideb.inf.survey.web.survey.transformer.QuestionStatisticDtoTransformer;
import hu.unideb.inf.survey.web.survey.transformer.SurveyQuestionDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CheckSurveyController {

    private static final String REQUEST_MAPPING = "/checkSurvey";

    private final SelectedAnswerService selectedAnswerService;
    private final SurveyQuestionService surveyQuestionService;

    private final SurveyQuestionDtoTransformer surveyQuestionDtoTransformer;
    private final QuestionStatisticDtoTransformer questionStatisticDtoTransformer;

    @Autowired
    public CheckSurveyController(SelectedAnswerService selectedAnswerService, SurveyQuestionService surveyQuestionService, SurveyQuestionDtoTransformer surveyQuestionDtoTransformer, QuestionStatisticDtoTransformer questionStatisticDtoTransformer) {
        this.selectedAnswerService = selectedAnswerService;
        this.surveyQuestionService = surveyQuestionService;
        this.surveyQuestionDtoTransformer = surveyQuestionDtoTransformer;
        this.questionStatisticDtoTransformer = questionStatisticDtoTransformer;
    }

    @GetMapping(REQUEST_MAPPING)
    public String checkSurveyController(){
        return "checkSurvey";
    }

    @ModelAttribute("surveyData")
    public SurveyDataDto generateSurveyData(@RequestParam(name = "id") long surveyId){
        SurveyDataDto surveyData = new SurveyDataDto();

        List<SurveyQuestionDomain> questions = surveyQuestionService.findSurveyQuestionsBySurveyId(surveyId);
        Long numberOfSurveyTaken = selectedAnswerService.getNumberOfSurveyTaken(surveyId);
        List<QuestionStatisticDto> questionStatistics = questionStatisticDtoTransformer.from(questions, numberOfSurveyTaken);
        surveyData.setQuestionStatistics(questionStatistics);

        surveyData.setCountOfSurveyTaken(numberOfSurveyTaken);

        return surveyData;
    }
}
