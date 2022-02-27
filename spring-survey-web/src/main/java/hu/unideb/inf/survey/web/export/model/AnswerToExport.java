package hu.unideb.inf.survey.web.export.model;

public class AnswerToExport {
    private String answerText;

    private long numberOfPicks;

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public long getNumberOfPicks() {
        return numberOfPicks;
    }

    public void setNumberOfPicks(long numberOfPicks) {
        this.numberOfPicks = numberOfPicks;
    }
}
