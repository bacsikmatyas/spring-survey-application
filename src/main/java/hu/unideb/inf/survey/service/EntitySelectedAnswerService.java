package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.domain.entity.SelectedAnswer;
import hu.unideb.inf.survey.domain.entity.User;
import hu.unideb.inf.survey.domain.repository.QuestionAnswerRepository;
import hu.unideb.inf.survey.domain.repository.SelectedAnswerRepository;
import hu.unideb.inf.survey.domain.repository.UserRepository;
import hu.unideb.inf.survey.service.exception.UnsavableAnswerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntitySelectedAnswerService implements SelectedAnswerService {
    private final SelectedAnswerRepository selectedAnswerRepository;
    private final UserRepository userRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public EntitySelectedAnswerService(SelectedAnswerRepository selectedAnswerRepository, UserRepository userRepository, QuestionAnswerRepository questionAnswerRepository) {
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.userRepository = userRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public void saveNewSelectedAnswer(long questionAnswerId, long userId) {

        Optional<User> user = userRepository.findById(userId);
        Optional<QuestionAnswer> answer = questionAnswerRepository.findById(questionAnswerId);

        if(user.isPresent() && answer.isPresent()){
            SelectedAnswer selectedAnswer = new SelectedAnswer();
            selectedAnswer.setAnswer(answer.get());
            selectedAnswer.setUser(user.get());
            SelectedAnswer savedAnswer = selectedAnswerRepository.save(selectedAnswer);
            System.out.printf("%s is saved!%n", savedAnswer);
        }else{
            throw new UnsavableAnswerException("Cannot find th user or the answer to be selected in the database!");
        }

    }
}
