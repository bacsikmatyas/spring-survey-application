package hu.unideb.inf.survey.web.export.model;

import java.util.List;

public class QuestionToExport {
    private String questionText;

    private List<AnswerToExport> answers;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerToExport> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerToExport> answers) {
        this.answers = answers;
    }
}
