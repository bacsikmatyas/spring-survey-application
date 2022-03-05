package hu.unideb.inf.survey.web.question.model;

import hu.unideb.inf.survey.web.answer.model.AnswerDto;

import java.util.List;

public class QuestionDto {

    private long id;

    private String questionText;

    private List<AnswerDto> answers;

    private boolean multiselect;

    private boolean freetext;

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isFreetext() {
        return freetext;
    }

    public void setFreetext(boolean freetext) {
        this.freetext = freetext;
    }
}
