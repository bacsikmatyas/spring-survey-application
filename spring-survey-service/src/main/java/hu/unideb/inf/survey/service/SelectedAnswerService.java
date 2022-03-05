package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;

import java.util.List;

public interface SelectedAnswerService {
    void saveNewSelectedAnswer(long questionAnswerId, long userId, String freeText);

    List<SelectedAnswerDomain> getSelectedAnswersBySurveyId(long surveyId);

    Long getNumberOfSurveyTaken(long surveyId);

    Double getNumberOfPicksOnAnAnswer(long id);
}
