package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;

import java.util.List;

public interface SelectedAnswerService {
    void saveNewSelectedAnswers(Long surveyId, List<SelectedAnswerDomain> selectedAnswerDomains, long userId);

    List<SelectedAnswerDomain> getSelectedAnswersBySurveyId(long surveyId);

    Double getNumberOfPicksOnAnAnswer(long id);

    Double getNumberOfPicksOnFreetextAnswer(String freetext);

    List<SelectedAnswerDomain> getGivenFreeTextAnswers(Long freeTextAnswerId);
}
