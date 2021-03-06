package hu.unideb.inf.survey.domain.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SurveyQuestion")
public class SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<QuestionAnswer> answers;

    @Column(name = "questionText", nullable = false)
    private String questionText;

    @Column(name = "multiselect")
    private boolean multiselect;

    @Column(name = "freetext")
    private boolean freetext;

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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

    @Override
    public String toString() {
        return "SurveyQuestion{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
