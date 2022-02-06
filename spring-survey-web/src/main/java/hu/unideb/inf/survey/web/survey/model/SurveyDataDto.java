package hu.unideb.inf.survey.web.survey.model;

import hu.unideb.inf.survey.web.question.model.QuestionStatisticDto;

import java.util.List;

public class SurveyDataDto {

    private List<QuestionStatisticDto> questionStatistics;

    private Long countOfSurveyTaken;

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