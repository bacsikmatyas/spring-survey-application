package hu.unideb.inf.survey.service;

import hu.unideb.inf.survey.service.domain.QuestionAnswerDomain;

public interface QuestionAnswerService {
    QuestionAnswerDomain findQuestionAnswerById(Long answerId);

    Long getQuestionIdByAnswerId(Long answerId);

    void editAnswerText(Long answerId, String answerText);

    void deleteQuestionAnswerById(Long answerId);
}
