package hu.unideb.inf.survey.service.transformer;

import hu.unideb.inf.survey.domain.entity.QuestionAnswer;
import hu.unideb.inf.survey.domain.repository.QuestionAnswerRepository;
import hu.unideb.inf.survey.service.QuestionAnswerService;
import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntityQuestionAnswerService implements QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerTransformer questionAnswerTransformer;

    private final Logger logger = LoggerFactory.getLogger(EntityQuestionAnswerService.class);

    @Autowired
    public EntityQuestionAnswerService(QuestionAnswerRepository questionAnswerRepository, QuestionAnswerTransformer questionAnswerTransformer) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.questionAnswerTransformer = questionAnswerTransformer;
    }

    @Override
    public QuestionAnswerDomain findQuestionAnswerById(Long answerId) {
        Optional<QuestionAnswer> optionalQuestionAnswer = questionAnswerRepository.findById(answerId);
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswer questionAnswer = optionalQuestionAnswer.get();
            logger.info("Question answer found by the id of {}", answerId);
            return questionAnswerTransformer.from(questionAnswer);
        } else {
            logger.info("Question answer not found by the id of {}", answerId);
            return null;
        }
    }

    @Override
    public Long getQuestionIdByAnswerId(Long answerId) {
        Optional<QuestionAnswer> optionalQuestionAnswer = questionAnswerRepository.findById(answerId);
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswer questionAnswer = optionalQuestionAnswer.get();
            Long questionId = questionAnswer.getQuestion().getId();
            logger.info("The id of the question with the answer with the id of {} is: {}", answerId, questionId);
            return questionId;
        } else {
            logger.info("Answer with the id of {} not found!", answerId);
            return null;
        }
    }

    @Override
    public void editAnswerText(Long answerId, String answerText) {
        Optional<QuestionAnswer> optionalQuestionAnswer = questionAnswerRepository.findById(answerId);
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswer questionAnswer = optionalQuestionAnswer.get();
            questionAnswer.setAnswerText(answerText);
            questionAnswerRepository.save(questionAnswer);
            logger.info("Answer text modified on the answer with id of {}", answerId);
        } else {
            logger.info("Answer with the id of {} cannot be found, no modification!", answerId);
        }
    }

    @Override
    public void deleteQuestionAnswerById(Long answerId) {
        Optional<QuestionAnswer> optionalQuestionAnswer = questionAnswerRepository.findById(answerId);
        if (optionalQuestionAnswer.isPresent()) {
            QuestionAnswer questionAnswer = optionalQuestionAnswer.get();
            questionAnswer.setQuestion(null);
            questionAnswerRepository.delete(questionAnswer);
            logger.info("Answer with the id of {} deleted!", answerId);
        } else {
            logger.error("There is no answer with the id of {}!", answerId);
        }
    }
}
