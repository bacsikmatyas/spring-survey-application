package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.QuestionAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SelectedAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SurveyQuestionRepository;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import hu.unideb.inf.survey.service.domain.SelectedAnswerDomain;
import hu.unideb.inf.survey.service.exception.UnsavableAnswerException;
import hu.unideb.inf.survey.service.transformer.SelectedAnswerTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntitySelectedAnswerService implements SelectedAnswerService {
    private final SelectedAnswerRepository selectedAnswerRepository;
    private final UserRepository userRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final SelectedAnswerTransformer selectedAnswerTransformer;
    private final SurveyQuestionRepository surveyQuestionRepository;

    private final Logger logger = LoggerFactory.getLogger(EntitySelectedAnswerService.class);

    @Autowired
    public EntitySelectedAnswerService(SelectedAnswerRepository selectedAnswerRepository, UserRepository userRepository, QuestionAnswerRepository questionAnswerRepository, SelectedAnswerTransformer selectedAnswerTransformer, SurveyQuestionRepository surveyQuestionRepository) {
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.userRepository = userRepository;
        this.questionAnswerRepository = questionAnswerRepository;
        this.selectedAnswerTransformer = selectedAnswerTransformer;
        this.surveyQuestionRepository = surveyQuestionRepository;
    }

    @Override
    public void saveNewSelectedAnswer(long questionAnswerId, long userId, String freeText) {
        Optional<User> user = userRepository.findById(userId);
        Optional<QuestionAnswer> answer = questionAnswerRepository.findById(questionAnswerId);
        if (user.isPresent() && answer.isPresent()) {
            SelectedAnswer selectedAnswer = new SelectedAnswer();
            selectedAnswer.setAnswer(answer.get());
            selectedAnswer.setUser(user.get());
            selectedAnswer.setFreetext(freeText);
            SelectedAnswer savedAnswer = selectedAnswerRepository.save(selectedAnswer);
            logger.info("'{}' answer with the id of {} is saved!", answer.get().getAnswerText(), savedAnswer.getId());
        } else {
            throw new UnsavableAnswerException("Cannot find the user or the answer to be selected in the database!");
        }
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
    public Long getNumberOfSurveyTaken(long surveyId) {
        long allAnswerGiven = selectedAnswerRepository.countSelectedAnswersByAnswer_Question_Survey_Id(surveyId);
        long correctNumberOfAnswers = allAnswerGiven / surveyQuestionRepository.countSurveyQuestionsBySurvey_Id(surveyId);
        logger.info("{} user took the survey with id of {}!", correctNumberOfAnswers, surveyId);
        return correctNumberOfAnswers;
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
