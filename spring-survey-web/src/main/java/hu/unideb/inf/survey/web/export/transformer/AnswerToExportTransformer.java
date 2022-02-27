package hu.unideb.inf.survey.web.export.transformer;

import hu.unideb.inf.survey.service.SelectedAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.web.export.model.AnswerToExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerToExportTransformer {
    private final SelectedAnswerService selectedAnswerService;

    @Autowired
    public AnswerToExportTransformer(SelectedAnswerService selectedAnswerService) {
        this.selectedAnswerService = selectedAnswerService;
    }

    public AnswerToExport from(QuestionAnswerDomain answerDomain){
        AnswerToExport answerToExport = new AnswerToExport();
        answerToExport.setAnswerText(answerDomain.getAnswerText());
        answerToExport.setNumberOfPicks(selectedAnswerService.getNumberOfPicksOnAnAnswer(answerDomain.getId()).longValue());
        return answerToExport;
    }
}
