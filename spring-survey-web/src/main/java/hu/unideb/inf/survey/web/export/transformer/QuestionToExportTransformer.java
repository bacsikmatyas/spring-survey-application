package hu.unideb.inf.survey.web.export.transformer;

import hu.unideb.inf.survey.service.SelectedAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.web.export.model.AnswerToExport;
import hu.unideb.inf.survey.web.export.model.QuestionToExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionToExportTransformer {
    private final AnswerToExportTransformer answerToExportTransformer;

    private final SelectedAnswerService selectedAnswerService;

    @Autowired
    public QuestionToExportTransformer(AnswerToExportTransformer answerToExportTransformer, SelectedAnswerService selectedAnswerService) {
        this.answerToExportTransformer = answerToExportTransformer;
        this.selectedAnswerService = selectedAnswerService;
    }

    public QuestionToExport from(SurveyQuestionDomain questionDomain) {
        QuestionToExport questionToExport = new QuestionToExport();
        questionToExport.setQuestionText(questionDomain.getQuestionText());

        List<AnswerToExport> answers = new ArrayList<>();
        if (questionDomain.isFreetext()) {
            Long freeTextAnswerId = questionDomain.getAnswers().get(0).getId();
            Set<String> givenFreeTexts = selectedAnswerService.getGivenFreeTextAnswers(freeTextAnswerId).stream()
                    .map(SelectedAnswerDomain::getFreetext)
                    .collect(Collectors.toSet());
            for (String answer : givenFreeTexts) {
                answers.add(generateFreeTextAnswerToExport(answer));
            }
        } else {
            for (QuestionAnswerDomain answerDomain : questionDomain.getAnswers()) {
                answers.add(answerToExportTransformer.from(answerDomain));
            }
        }

        questionToExport.setAnswers(answers);

        return questionToExport;
    }

    private AnswerToExport generateFreeTextAnswerToExport(String answer) {
        AnswerToExport answerToExport = new AnswerToExport();
        answerToExport.setAnswerText(answer);
        answerToExport.setNumberOfPicks(selectedAnswerService.getNumberOfPicksOnFreetextAnswer(answer).longValue());
        return answerToExport;
    }
}
