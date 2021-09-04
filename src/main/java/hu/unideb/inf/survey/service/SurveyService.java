package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.SurveyDomain;

import java.util.List;

public interface SurveyService {
    List<SurveyDomain> findAllOpenSurveys();
}
