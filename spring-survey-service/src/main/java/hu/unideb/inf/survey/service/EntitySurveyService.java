package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.Survey;
import hu.unideb.inf.survey.domain.repository.SurveyRepository;
import hu.unideb.inf.survey.service.domain.SurveyDomain;
import hu.unideb.inf.survey.service.transformer.SurveyTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntitySurveyService implements SurveyService{
    private final SurveyRepository surveyRepository;
    private final SurveyTransformer surveyTransformer;

    Logger logger = LoggerFactory.getLogger(EntitySurveyService.class);

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
        logger.info("{} open survey(s) found!", surveyDomains.size());
        return surveyDomains;
    }

    @Override
    public List<SurveyDomain> findUserSurveys(long userId) {
        List<Survey> surveys = surveyRepository.findSurveysByUser_Id(userId);
        List<SurveyDomain> surveyDomains = new ArrayList<>();
        for (Survey survey: surveys){
            surveyDomains.add(surveyTransformer.from(survey));
        }
        logger.info("{} survey found with the user id of {}!", surveyDomains.size(), userId);
        return surveyDomains;
    }

    @Override
    public SurveyDomain findSurveyById(long surveyId) {
        SurveyDomain surveyDomain =  new SurveyDomain();
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        if (surveyOptional.isPresent()){
            surveyDomain = surveyTransformer.from(surveyOptional.get());
            logger.info("Survey found with the id of {}!",surveyId);
        }
        else {
            logger.info("Survey with the id of {} not found!",surveyId);
        }
        return surveyDomain;
    }

    @Override
    public void editSurveyTexts(long surveyId, String title, String description) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if (optionalSurvey.isPresent()){
            Survey survey = optionalSurvey.get();
            survey.setTitle(title);
            survey.setDescription(description);
            surveyRepository.save(survey);
            logger.info("Survey text modified on the survey with id of {}", surveyId);
        }
        else {
            logger.info("Survey with the id of {} cannot be found, no modification!", surveyId);
        }
    }
}
