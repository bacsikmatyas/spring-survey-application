package hu.unideb.inf.survey.web.survey.transformer;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.web.survey.model.AnswerDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionAnswerDomainTransformer {
    public AnswerDto from(QuestionAnswerDomain questionAnswerDomain){
        AnswerDto answerDto = new AnswerDto();

        answerDto.setId(questionAnswerDomain.getId());
        answerDto.setAnswerText(questionAnswerDomain.getAnswerText());

        return answerDto;
    }
}
