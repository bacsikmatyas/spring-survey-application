package hu.unideb.inf.survey.web.survey.transformer;

import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.question.transformer.QuestionStatisticDtoTransformer;
import hu.unideb.inf.survey.web.survey.model.SurveyDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyDataDtoTransformer {

    private final QuestionStatisticDtoTransformer questionStatisticDtoTransformer;

    @Autowired
    public SurveyDataDtoTransformer(QuestionStatisticDtoTransformer questionStatisticDtoTransformer) {
        this.questionStatisticDtoTransformer = questionStatisticDtoTransformer;
    }

    public SurveyDataDto from (SurveyDomain surveyDomain){
        SurveyDataDto surveyDataDto = new SurveyDataDto();
        surveyDataDto.setId(surveyDomain.getId());
        surveyDataDto.setCountOfSurveyTaken(surveyDomain.getSurveyTaken());
        surveyDataDto.setQuestionStatistics(questionStatisticDtoTransformer.from(surveyDomain.getQuestions(), surveyDomain.getSurveyTaken()));
        return surveyDataDto;
    }
}
