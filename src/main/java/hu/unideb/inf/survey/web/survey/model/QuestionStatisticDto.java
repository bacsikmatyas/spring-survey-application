package hu.unideb.inf.survey.web.survey.model;

import java.util.List;

public class QuestionStatisticDto {
    private String questionText;

    private List<AnswerStatisticDto> answerStatistics;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerStatisticDto> getAnswerStatistics() {
        return answerStatistics;
    }

    public void setAnswerStatistics(List<AnswerStatisticDto> answerStatistics) {
        this.answerStatistics = answerStatistics;
    }
}
