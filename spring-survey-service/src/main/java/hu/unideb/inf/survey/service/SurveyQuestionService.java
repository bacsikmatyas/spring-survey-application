package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;

import java.util.List;

public interface SurveyQuestionService {
    List<SurveyQuestionDomain> findSurveyQuestionsBySurveyId(Long surveyId);

    void deleteSurveyQuestionById(Long questionId);

    SurveyQuestionDomain findSurveyQuestionById(Long questionId);

    void editQuestionText(Long questionId, String questionText, boolean multiselect);

    Long getSurveyIdByQuestionId(Long questionId);

    Long saveNewQuestion(SurveyQuestionDomain surveyQuestionDomain);
}
