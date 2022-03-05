package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import org.springframework.stereotype.Component;

@Component
public class SelectedAnswerTransformer {

    public SelectedAnswerDomain from(SelectedAnswer selectedAnswer){
        SelectedAnswerDomain selectedAnswerDomain = new SelectedAnswerDomain();

        selectedAnswerDomain.setId(selectedAnswer.getId());
        selectedAnswerDomain.setQuestionAnswerId(selectedAnswer.getAnswer().getId());
        selectedAnswerDomain.setUserId(selectedAnswer.getUser().getId());
        selectedAnswerDomain.setFreetext(selectedAnswer.getFreetext());

        return selectedAnswerDomain;
    }
}
