package hu.unideb.inf.survey.web.survey.transformer;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.survey.model.AnswerDto;
import hu.unideb.inf.survey.web.survey.model.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyQuestionDomainTransformer {

    private final QuestionAnswerDomainTransformer questionAnswerDomainTransformer;

    @Autowired
    public SurveyQuestionDomainTransformer(QuestionAnswerDomainTransformer questionAnswerDomainTransformer) {
        this.questionAnswerDomainTransformer = questionAnswerDomainTransformer;
    }

    public QuestionDto from(SurveyQuestionDomain surveyQuestionDomain){
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(surveyQuestionDomain.getId());
        questionDto.setQuestionText(surveyQuestionDomain.getQuestionText());

        List<AnswerDto> answerDtos = new ArrayList<>();
        for(QuestionAnswerDomain questionAnswerDomain: surveyQuestionDomain.getAnswers()){
            AnswerDto tmp = questionAnswerDomainTransformer.from(questionAnswerDomain);
            answerDtos.add(tmp);
        }
        questionDto.setAnswers(answerDtos);

        return questionDto;
    }
}
