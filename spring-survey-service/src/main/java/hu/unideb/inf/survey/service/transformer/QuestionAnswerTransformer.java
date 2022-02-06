package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import org.springframework.stereotype.Component;

@Component
public class QuestionAnswerTransformer {
    public QuestionAnswerDomain from(QuestionAnswer questionAnswer){
        QuestionAnswerDomain questionAnswerDomain = new QuestionAnswerDomain();

        questionAnswerDomain.setId(questionAnswer.getId());
        questionAnswerDomain.setAnswerText(questionAnswer.getAnswerText());

        return questionAnswerDomain;
    }

    public QuestionAnswer from(QuestionAnswerDomain questionAnswerDomain){
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setAnswerText(questionAnswerDomain.getAnswerText());
        return questionAnswer;
    }
}
