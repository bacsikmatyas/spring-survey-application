package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.Survey;
import hu.unideb.inf.survey.domain.entity.SurveyQuestion;
import hu.unideb.inf.survey.domain.repository.SurveyQuestionRepository;
import hu.unideb.inf.survey.domain.repository.SurveyRepository;
import hu.unideb.inf.survey.service.domain.SurveyQuestionDomain;
import hu.unideb.inf.survey.service.transformer.SurveyQuestionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntitySurveyQuestionService implements SurveyQuestionService {
    private final SurveyQuestionRepository surveyQuestionRepository;
    private final SurveyQuestionTransformer surveyQuestionTransformer;
    private final SurveyRepository surveyRepository;

    private final Logger logger = LoggerFactory.getLogger(EntitySurveyQuestionService.class);

    @Autowired
    public EntitySurveyQuestionService(SurveyQuestionRepository surveyQuestionRepository, SurveyQuestionTransformer surveyQuestionTransformer, SurveyRepository surveyRepository) {
        this.surveyQuestionTransformer = surveyQuestionTransformer;
        this.surveyQuestionRepository = surveyQuestionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<SurveyQuestionDomain> findSurveyQuestionsBySurveyId(Long surveyId) {
        Iterable<SurveyQuestion> surveyQuestionsBySurveyId = surveyQuestionRepository.findSurveyQuestionsBySurveyId(surveyId);
        List<SurveyQuestionDomain> surveyQuestionDomains = new ArrayList<>();
        for (SurveyQuestion surveyQuestion : surveyQuestionsBySurveyId) {
            surveyQuestionDomains.add(surveyQuestionTransformer.from(surveyQuestion));
        }
        logger.info("{} question found for the survey with id of {}!", surveyQuestionDomains.size(), surveyId);
        return surveyQuestionDomains;
    }

    @Override
    public void deleteSurveyQuestionById(Long questionId) {
        Optional<SurveyQuestion> question = surveyQuestionRepository.findById(questionId);
        if (question.isPresent()) {
            SurveyQuestion surveyQuestion = question.get();
            surveyQuestion.setSurvey(null);
            surveyQuestionRepository.delete(surveyQuestion);
            logger.info("Question with the id of {} deleted!", questionId);
        } else {
            logger.error("There is no question with the id of {}!", questionId);
        }
    }

    @Override
    public SurveyQuestionDomain findSurveyQuestionById(Long questionId) {
        Optional<SurveyQuestion> question = surveyQuestionRepository.findById(questionId);
        if (question.isPresent()) {
            SurveyQuestion surveyQuestion = question.get();
            logger.info("Survey question found by the id of {}", questionId);
            return surveyQuestionTransformer.from(surveyQuestion);
        } else {
            logger.info("Survey question not found by the id of {}", questionId);
            return null;
        }
    }

    @Override
    public void editQuestionText(Long questionId, String questionText, boolean multiselect) {
        Optional<SurveyQuestion> questionOptional = surveyQuestionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            SurveyQuestion surveyQuestion = questionOptional.get();
            surveyQuestion.setQuestionText(questionText);
            surveyQuestion.setMultiselect(multiselect);
            surveyQuestionRepository.save(surveyQuestion);
            logger.info("Question text modified on the question with id of {}", questionId);
        } else {
            logger.info("Question with the id of {} cannot be found, no modification!", questionId);
        }
    }

    @Override
    public Long getSurveyIdByQuestionId(Long questionId) {
        Optional<SurveyQuestion> optionalSurveyQuestion = surveyQuestionRepository.findById(questionId);
        if (optionalSurveyQuestion.isPresent()) {
            SurveyQuestion surveyQuestion = optionalSurveyQuestion.get();
            Long surveyId = surveyQuestion.getSurvey().getId();
            logger.info("The id of the survey with the question with the id of {} is: {}", questionId, surveyId);
            return surveyId;
        } else {
            logger.info("Question with the id of {} not found!", questionId);
            return null;
        }
    }

    @Override
    public void saveNewQuestion(SurveyQuestionDomain surveyQuestionDomain) {
        SurveyQuestion surveyQuestion = surveyQuestionTransformer.from(surveyQuestionDomain);
        Long surveyId = surveyQuestionDomain.getSurvey().getId();
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if (optionalSurvey.isPresent()) {
            Survey survey = optionalSurvey.get();
            surveyQuestion.setSurvey(survey);
        } else {
            logger.info("Survey not found with id {}", surveyId);
        }
        SurveyQuestion savedQuestion = surveyQuestionRepository.save(surveyQuestion);
        logger.info("Question saved with id {}", savedQuestion.getId());
    }
}
