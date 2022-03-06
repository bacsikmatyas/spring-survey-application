package hu.unideb.inf.survey.web.answer.transformer;

import hu.unideb.inf.survey.service.SelectedAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import hu.unideb.inf.survey.web.answer.model.AnswerStatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AnswerStatisticDtoTransformer {

    private final SelectedAnswerService selectedAnswerService;

    @Autowired
    public AnswerStatisticDtoTransformer(SelectedAnswerService selectedAnswerService) {
        this.selectedAnswerService = selectedAnswerService;
    }

    public List<AnswerStatisticDto> from(List<QuestionAnswerDomain> answers, Long numberOfSurveyTaken, boolean freetext) {
        return freetext ? transformFreeTextAnswers(answers, numberOfSurveyTaken) : transformAnswers(answers, numberOfSurveyTaken);
    }

    private List<AnswerStatisticDto> transformAnswers(List<QuestionAnswerDomain> answers, Long numberOfSurveyTaken) {
        List<AnswerStatisticDto> answerStatistics = new ArrayList<>();
        for (QuestionAnswerDomain answer : answers) {
            AnswerStatisticDto answerStatistic = new AnswerStatisticDto();
            answerStatistic.setAnswerText(answer.getAnswerText());
            double percentage = 0D;
            if (numberOfSurveyTaken > 0) {
                percentage = (selectedAnswerService.getNumberOfPicksOnAnAnswer(answer.getId()) / numberOfSurveyTaken) * 100D;
                percentage = Math.round(percentage * 100) / 100D;
            }
            answerStatistic.setPercentage(percentage);
            answerStatistics.add(answerStatistic);
        }
        return answerStatistics;
    }

    private List<AnswerStatisticDto> transformFreeTextAnswers(List<QuestionAnswerDomain> answers, Long numberOfSurveyTaken) {
        List<AnswerStatisticDto> answerStatistics = new ArrayList<>();
        QuestionAnswerDomain answerDomain = answers.get(0);
        Long freeTextAnswerId = answerDomain.getId();
        Set<String> givenFreeTexts = selectedAnswerService.getGivenFreeTextAnswers(freeTextAnswerId).stream()
                .map(SelectedAnswerDomain::getFreetext)
                .collect(Collectors.toSet());
        for (String answer : givenFreeTexts) {
            AnswerStatisticDto answerStatistic = new AnswerStatisticDto();
            answerStatistic.setAnswerText(answer);
            double percentage = 0D;
            if (numberOfSurveyTaken > 0) {
                percentage = (selectedAnswerService.getNumberOfPicksOnFreetextAnswer(answer) / numberOfSurveyTaken) * 100D;
                percentage = Math.round(percentage * 100) / 100D;
            }
            answerStatistic.setPercentage(percentage);
            answerStatistics.add(answerStatistic);
        }
        return answerStatistics;
    }
}
