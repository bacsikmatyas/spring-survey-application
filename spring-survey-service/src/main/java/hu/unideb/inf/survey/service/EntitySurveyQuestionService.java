package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import hu.unideb.inf.survey.domain.repository.SurveyQuestionRepository;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.service.transformer.SurveyQuestionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntitySurveyQuestionService implements SurveyQuestionService {
    private final SurveyQuestionRepository surveyQuestionRepository;

    private final SurveyQuestionTransformer surveyQuestionTransformer;

    @Autowired
    public EntitySurveyQuestionService(SurveyQuestionRepository surveyQuestionRepository, SurveyQuestionTransformer surveyQuestionTransformer) {
        this.surveyQuestionTransformer = surveyQuestionTransformer;
        this.surveyQuestionRepository = surveyQuestionRepository;
    }

    @Override
    public List<SurveyQuestionDomain> findSurveyQuestionsBySurveyId(Long surveyId) {
        Iterable<SurveyQuestion> surveyQuestionsBySurveyId = surveyQuestionRepository.findSurveyQuestionsBySurveyId(surveyId);

        List<SurveyQuestionDomain> surveyQuestionDomains = new ArrayList<>();
        for (SurveyQuestion surveyQuestion: surveyQuestionsBySurveyId){
            surveyQuestionDomains.add(surveyQuestionTransformer.from(surveyQuestion));
        }

        return surveyQuestionDomains;
    }
}
