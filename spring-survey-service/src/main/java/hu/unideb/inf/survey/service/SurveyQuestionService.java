package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;

import java.util.List;

public interface SurveyQuestionService {
    List<SurveyQuestionDomain> findSurveyQuestionsBySurveyId(Long surveyId);
}
