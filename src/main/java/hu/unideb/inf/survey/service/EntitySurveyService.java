package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.Survey;
import hu.unideb.inf.survey.domain.repository.SurveyRepository;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.transformer.SurveyTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntitySurveyService implements SurveyService{

    private final SurveyRepository surveyRepository;

    private final SurveyTransformer surveyTransformer;

    @Autowired
    public EntitySurveyService(SurveyRepository surveyRepository, SurveyTransformer surveyTransformer) {
        this.surveyRepository = surveyRepository;
        this.surveyTransformer = surveyTransformer;
    }

    @Override
    public List<SurveyDomain> findAllOpenSurveys() {
        Iterable<Survey> surveys = surveyRepository.findSurveysByOpen(true);

        List<SurveyDomain> surveyDomains = new ArrayList<>();
        for (Survey survey: surveys){
            surveyDomains.add(surveyTransformer.from(survey));
        }

        return surveyDomains;
    }
}
