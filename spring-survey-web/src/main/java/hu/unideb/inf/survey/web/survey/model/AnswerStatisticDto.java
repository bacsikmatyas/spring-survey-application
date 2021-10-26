package hu.unideb.inf.survey.web.survey.model;

public class AnswerStatisticDto {
    private String answerText;

    private Double percentage;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
