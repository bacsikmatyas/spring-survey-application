package hu.unideb.inf.survey.web.survey.model;

import hu.unideb.inf.survey.web.question.model.QuestionDto;

import java.util.List;

public class SurveyDto {
    private long id;

    private String surveyTitle;

    private String surveyDescription;

    private List<QuestionDto> questions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
