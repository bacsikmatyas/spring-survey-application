package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyQuestionTransformer {

    private final QuestionAnswerTransformer questionAnswerTransformer;

    @Autowired
    public SurveyQuestionTransformer(QuestionAnswerTransformer questionAnswerTransformer) {
        this.questionAnswerTransformer = questionAnswerTransformer;
    }

    public SurveyQuestionDomain from(SurveyQuestion surveyQuestion) {
        SurveyQuestionDomain surveyQuestionDomain = new SurveyQuestionDomain();

        surveyQuestionDomain.setId(surveyQuestion.getId());
        surveyQuestionDomain.setQuestionText(surveyQuestion.getQuestionText());
        surveyQuestionDomain.setMultiselect(surveyQuestion.isMultiselect());

        List<QuestionAnswerDomain> questionAnswerDomains = new ArrayList<>();
        for (QuestionAnswer questionAnswer : surveyQuestion.getAnswers()) {
            QuestionAnswerDomain tmp = questionAnswerTransformer.from(questionAnswer);
            tmp.setQuestion(surveyQuestionDomain);
            questionAnswerDomains.add(tmp);
        }
        surveyQuestionDomain.setAnswers(questionAnswerDomains);

        return surveyQuestionDomain;
    }

    public SurveyQuestion from(SurveyQuestionDomain surveyQuestionDomain) {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionText(surveyQuestionDomain.getQuestionText());
        surveyQuestion.setMultiselect(surveyQuestionDomain.isMultiselect());
        return surveyQuestion;
    }
}
