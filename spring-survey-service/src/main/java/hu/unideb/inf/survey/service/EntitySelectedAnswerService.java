package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.QuestionAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SelectedAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SurveyRepository;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import hu.unideb.inf.survey.service.exception.UnsavableAnswerException;
import hu.unideb.inf.survey.service.transformer.SelectedAnswerTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntitySelectedAnswerService implements SelectedAnswerService {
    private final SelectedAnswerRepository selectedAnswerRepository;
    private final UserRepository userRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final SurveyRepository surveyRepository;
    private final SelectedAnswerTransformer selectedAnswerTransformer;

    private final Logger logger = LoggerFactory.getLogger(EntitySelectedAnswerService.class);

    @Autowired
    public EntitySelectedAnswerService(SelectedAnswerRepository selectedAnswerRepository, UserRepository userRepository, QuestionAnswerRepository questionAnswerRepository, SurveyRepository surveyRepository, SelectedAnswerTransformer selectedAnswerTransformer) {
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.userRepository = userRepository;
        this.questionAnswerRepository = questionAnswerRepository;
        this.surveyRepository = surveyRepository;
        this.selectedAnswerTransformer = selectedAnswerTransformer;
    }

    @Override
    @Transactional
    public void saveNewSelectedAnswers(Long surveyId, List<SelectedAnswerDomain> selectedAnswerDomains, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UnsavableAnswerException("Cannot find the user in the database!");
        });
        Map<Long, QuestionAnswer> mapOfQuestionAnswers = getMapOfQuestionAnswers(selectedAnswerDomains);
        List<SelectedAnswer> selectedAnswers = getSelectedAnswersToBeSaved(selectedAnswerDomains, user, mapOfQuestionAnswers);
        selectedAnswerRepository.saveAll(selectedAnswers);
        surveyRepository.increaseTakenCount(surveyId);
        logger.info("{} answer is saved!", selectedAnswers.size());
    }

    private List<SelectedAnswer> getSelectedAnswersToBeSaved(List<SelectedAnswerDomain> selectedAnswerDomains, User user, Map<Long, QuestionAnswer> mapOfQuestionAnswers) {
        List<SelectedAnswer> selectedAnswers = new ArrayList<>();
        for (SelectedAnswerDomain selectedAnswerDomain: selectedAnswerDomains){
            SelectedAnswer selectedAnswer = new SelectedAnswer();
            selectedAnswer.setAnswer(mapOfQuestionAnswers.get(selectedAnswerDomain.getQuestionAnswerId()));
            selectedAnswer.setUser(user);
            selectedAnswer.setFreetext(selectedAnswerDomain.getFreetext());
            selectedAnswers.add(selectedAnswer);
        }
        return selectedAnswers;
    }

    private Map<Long, QuestionAnswer> getMapOfQuestionAnswers(List<SelectedAnswerDomain> selectedAnswerDomains) {
        List<Long> collectedAnswerIds = selectedAnswerDomains.stream().map(SelectedAnswerDomain::getQuestionAnswerId).collect(Collectors.toList());
        Iterable<QuestionAnswer> allById = questionAnswerRepository.findAllById(collectedAnswerIds);
        Map<Long, QuestionAnswer> questionAnswerMap = new HashMap<>();
        allById.forEach(questionAnswer -> questionAnswerMap.put(questionAnswer.getId(), questionAnswer));
        if (collectedAnswerIds.size() < questionAnswerMap.size()){
            throw new UnsavableAnswerException("One or more answer cannot be found!");
        }
        return questionAnswerMap;
    }

    @Override
    public List<SelectedAnswerDomain> getSelectedAnswersBySurveyId(long surveyId) {
        List<SelectedAnswer> selectedAnswers = selectedAnswerRepository.findSelectedAnswersByAnswer_Question_Survey_Id(surveyId);
        List<SelectedAnswerDomain> selectedAnswerDomains = new ArrayList<>();
        for (SelectedAnswer selectedAnswer : selectedAnswers) {
            SelectedAnswerDomain tmp = selectedAnswerTransformer.from(selectedAnswer);
            selectedAnswerDomains.add(tmp);
        }
        logger.info("{} answer found for the survey with id of {}!", selectedAnswerDomains.size(), surveyId);
        return selectedAnswerDomains;
    }

    @Override
    public Double getNumberOfPicksOnAnAnswer(long id) {
        Double numberOfPicks = selectedAnswerRepository.countSelectedAnswerByAnswer_Id(id);
        logger.info("The answer with id of {} was chosen {} time(s)!", id, String.format("%.0f", numberOfPicks));
        return numberOfPicks;
    }

    @Override
    public Double getNumberOfPicksOnFreetextAnswer(String freetext) {
        Double numberOfPicks = selectedAnswerRepository.countSelectedAnswerByFreetext(freetext);
        logger.info("The answer with text of '{}' was chosen {} time(s)!", freetext, String.format("%.0f", numberOfPicks));
        return numberOfPicks;
    }

    @Override
    public List<SelectedAnswerDomain> getGivenFreeTextAnswers(Long freeTextAnswerId) {
        List<SelectedAnswer> selectedAnswers = selectedAnswerRepository.findSelectedAnswersByAnswer_Id(freeTextAnswerId);
        List<SelectedAnswerDomain> selectedAnswerDomains = new ArrayList<>();
        for (SelectedAnswer selectedAnswer : selectedAnswers) {
            SelectedAnswerDomain tmp = selectedAnswerTransformer.from(selectedAnswer);
            selectedAnswerDomains.add(tmp);
        }
        logger.info("{} freetext answer found for the answer with id of {}!", selectedAnswerDomains.size(), freeTextAnswerId);
        return selectedAnswerDomains;
    }
}
