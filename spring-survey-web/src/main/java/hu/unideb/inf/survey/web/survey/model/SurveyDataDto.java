package hu.unideb.inf.survey.web.survey.model;

import hu.unideb.inf.survey.web.question.model.QuestionStatisticDto;

import java.util.List;

public class SurveyDataDto {
    private Long id;

    private List<QuestionStatisticDto> questionStatistics;

    private Long countOfSurveyTaken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountOfSurveyTaken() {
        return countOfSurveyTaken;
    }

    public void setCountOfSurveyTaken(Long countOfSurveyTaken) {
        this.countOfSurveyTaken = countOfSurveyTaken;
    }

    public List<QuestionStatisticDto> getQuestionStatistics() {
        return questionStatistics;
    }

    public void setQuestionStatistics(List<QuestionStatisticDto> questionStatistics) {
        this.questionStatistics = questionStatistics;
    }
}