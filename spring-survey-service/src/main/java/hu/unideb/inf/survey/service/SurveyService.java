package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SurveyDomain;

import java.util.List;

public interface SurveyService {
    List<SurveyDomain> findAllOpenSurveys();

    List<SurveyDomain> findUserSurveys(long userId);

    SurveyDomain findSurveyById(long surveyId);

    void editSurveyTexts(long surveyId, String title, String description);

    void saveNewSurvey(SurveyDomain surveyDomain);

    void changeSurveyState(long surveyId);
}
