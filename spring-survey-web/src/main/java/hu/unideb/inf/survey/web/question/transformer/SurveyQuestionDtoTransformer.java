package hu.unideb.inf.survey.web.question.transformer;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.answer.model.AnswerDto;
import hu.unideb.inf.survey.web.question.model.QuestionDto;
import hu.unideb.inf.survey.web.answer.transformer.QuestionAnswerDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyQuestionDtoTransformer {

    private final QuestionAnswerDtoTransformer questionAnswerDtoTransformer;

    @Autowired
    public SurveyQuestionDtoTransformer(QuestionAnswerDtoTransformer questionAnswerDtoTransformer) {
        this.questionAnswerDtoTransformer = questionAnswerDtoTransformer;
    }

    public QuestionDto from(SurveyQuestionDomain surveyQuestionDomain){
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(surveyQuestionDomain.getId());
        questionDto.setQuestionText(surveyQuestionDomain.getQuestionText());
        questionDto.setMultiselect(surveyQuestionDomain.isMultiselect());
        questionDto.setFreetext(surveyQuestionDomain.isFreetext());

        List<AnswerDto> answerDtos = new ArrayList<>();
        for(QuestionAnswerDomain questionAnswerDomain: surveyQuestionDomain.getAnswers()){
            AnswerDto tmp = questionAnswerDtoTransformer.from(questionAnswerDomain);
            answerDtos.add(tmp);
        }
        questionDto.setAnswers(answerDtos);

        return questionDto;
    }

    public List<QuestionDto> from(List<SurveyQuestionDomain> surveyQuestionDomains){
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (SurveyQuestionDomain surveyQuestionDomain : surveyQuestionDomains) {
            QuestionDto tmp = from(surveyQuestionDomain);
            questionDtos.add(tmp);
        }
        return questionDtos;
    }
}
