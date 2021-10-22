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

    @Autowired
    public EntitySelectedAnswerService(SelectedAnswerRepository selectedAnswerRepository, UserRepository userRepository, QuestionAnswerRepository questionAnswerRepository, SelectedAnswerTransformer selectedAnswerTransformer, SurveyQuestionRepository surveyQuestionRepository) {
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.userRepository = userRepository;
        this.questionAnswerRepository = questionAnswerRepository;
        this.selectedAnswerTransformer = selectedAnswerTransformer;
        this.surveyQuestionRepository = surveyQuestionRepository;
    }

    @Override
    public void saveNewSelectedAnswer(long questionAnswerId, long userId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<QuestionAnswer> answer = questionAnswerRepository.findById(questionAnswerId);

        if (user.isPresent() && answer.isPresent()) {
            SelectedAnswer selectedAnswer = new SelectedAnswer();
            selectedAnswer.setAnswer(answer.get());
            selectedAnswer.setUser(user.get());
            SelectedAnswer savedAnswer = selectedAnswerRepository.save(selectedAnswer);
            System.out.printf("%s is saved!%n", savedAnswer);
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

        return selectedAnswerDomains;
    }

    @Override
    public Long getNumberOfSurveyTaken(long surveyId) {
        long allAnswerGiven = selectedAnswerRepository.countSelectedAnswersByAnswer_Question_Survey_Id(surveyId);
        long correctNumberOfAnswers = allAnswerGiven / surveyQuestionRepository.countSurveyQuestionsBySurvey_Id(surveyId);
        System.out.format("%d answer found!\n", correctNumberOfAnswers);
        return correctNumberOfAnswers;
    }

    @Override
    public Double getNumberOfPicksOnAnAnswer(long id) {
        return selectedAnswerRepository.countSelectedAnswerByAnswer_Id(id);
    }

}
