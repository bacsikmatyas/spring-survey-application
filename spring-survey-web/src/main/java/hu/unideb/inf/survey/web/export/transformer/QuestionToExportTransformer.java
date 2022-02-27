package hu.unideb.inf.survey.web.export.transformer;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.export.model.AnswerToExport;
import hu.unideb.inf.survey.web.export.model.QuestionToExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionToExportTransformer {
    private final AnswerToExportTransformer answerToExportTransformer;

    @Autowired
    public QuestionToExportTransformer(AnswerToExportTransformer answerToExportTransformer) {
        this.answerToExportTransformer = answerToExportTransformer;
    }

    public QuestionToExport from(SurveyQuestionDomain questionDomain){
        QuestionToExport questionToExport = new QuestionToExport();
        questionToExport.setQuestionText(questionDomain.getQuestionText());

        List<AnswerToExport> answers = new ArrayList<>();
        for (QuestionAnswerDomain answerDomain: questionDomain.getAnswers()){
            answers.add(answerToExportTransformer.from(answerDomain));
        }
        questionToExport.setAnswers(answers);

        return questionToExport;
    }
}
