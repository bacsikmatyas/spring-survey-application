package hu.unideb.inf.survey.service.domain;

import hu.unideb.inf.survey.domain.entity.SurveyQuestion;

public class QuestionAnswerDomain {

    private Long id;

    private SurveyQuestionDomain question;

    private String answerText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurveyQuestionDomain getQuestion() {
        return question;
    }

    public void setQuestion(SurveyQuestionDomain question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
