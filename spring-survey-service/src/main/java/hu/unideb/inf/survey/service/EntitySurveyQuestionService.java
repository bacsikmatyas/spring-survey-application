package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import hu.unideb.inf.survey.domain.repository.SurveyQuestionRepository;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.service.transformer.SurveyQuestionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntitySurveyQuestionService implements SurveyQuestionService {
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyQuestionTransformer surveyQuestionTransformer;

    Logger logger = LoggerFactory.getLogger(EntitySurveyQuestionService.class);

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
        logger.info("{} question found for the survey with id of {}!", surveyQuestionDomains.size(), surveyId);
        return surveyQuestionDomains;
    }

    @Override
    public void deleteSurveyQuestionById(Long questionId) {
        logger.info("Deleting of question with the id of {}!", questionId);
        Optional<SurveyQuestion> question = surveyQuestionRepository.findById(questionId);
        if (question.isPresent()){
            SurveyQuestion surveyQuestion = question.get();
            surveyQuestion.setSurvey(null);
            surveyQuestionRepository.delete(surveyQuestion);
        }
        else{
            logger.error("There is no question with the id of {}!", questionId);
        }
    }
}
