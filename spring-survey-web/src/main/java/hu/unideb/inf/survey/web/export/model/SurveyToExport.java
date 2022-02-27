package hu.unideb.inf.survey.web.export.model;

import java.util.List;

public class SurveyToExport {
    private long numberOfSurveyTaken;

    private String surveyTitle;

    private List<QuestionToExport> questions;

    public long getNumberOfSurveyTaken() {
        return numberOfSurveyTaken;
    }

    public void setNumberOfSurveyTaken(long numberOfSurveyTaken) {
        this.numberOfSurveyTaken = numberOfSurveyTaken;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public List<QuestionToExport> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionToExport> questions) {
        this.questions = questions;
    }
}
