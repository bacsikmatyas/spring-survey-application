package hu.unideb.inf.survey.web.answer.transformer;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.web.answer.model.AnswerDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionAnswerDtoTransformer {
    public AnswerDto from(QuestionAnswerDomain questionAnswerDomain){
        AnswerDto answerDto = new AnswerDto();

        answerDto.setId(questionAnswerDomain.getId());
        answerDto.setAnswerText(questionAnswerDomain.getAnswerText());

        return answerDto;
    }
}
