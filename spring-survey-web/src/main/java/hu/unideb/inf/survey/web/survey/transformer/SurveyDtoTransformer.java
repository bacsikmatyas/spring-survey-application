package hu.unideb.inf.survey.web.survey.transformer;

import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import hu.unideb.inf.survey.web.survey.model.SurveyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SurveyDtoTransformer {
    private final SurveyQuestionDtoTransformer surveyQuestionDtoTransformer;

    @Autowired
    public SurveyDtoTransformer(SurveyQuestionDtoTransformer surveyQuestionDtoTransformer) {
        this.surveyQuestionDtoTransformer = surveyQuestionDtoTransformer;
    }

    public SurveyDto from(SurveyDomain surveyDomain){
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(surveyDomain.getId());
        surveyDto.setSurveyTitle(surveyDomain.getTitle());
        surveyDto.setSurveyDescription(surveyDomain.getDescription());

        List<QuestionDto> questionDtos = surveyQuestionDtoTransformer.from(surveyDomain.getQuestions());
        surveyDto.setQuestions(questionDtos);

        return surveyDto;
    }
}
