package hu.unideb.inf.survey.service.domain;

import java.util.List;

public class SurveyQuestionDomain {

    private Long id;

    private SurveyDomain survey;

    private List<QuestionAnswerDomain> answers;

    private String questionText;

    private boolean multiselect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurveyDomain getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyDomain survey) {
        this.survey = survey;
    }

    public List<QuestionAnswerDomain> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswerDomain> answers) {
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isMultiselect() {
        return multiselect;
    }

    public void setMultiselect(boolean multiselect) {
        this.multiselect = multiselect;
    }
}
