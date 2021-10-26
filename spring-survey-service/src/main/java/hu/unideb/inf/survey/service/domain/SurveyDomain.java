package hu.unideb.inf.survey.service.domain;

import java.util.List;

public class SurveyDomain {
    private Long id;

    private UserDomain user;

    private List<SurveyQuestionDomain> questions;

    private String title;

    private String description;

    private String type;

    private Boolean open;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public List<SurveyQuestionDomain> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestionDomain> questions) {
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
