package hu.unideb.inf.survey.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "SelectedAnswer")
public class SelectedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuestionAnswer answer;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(QuestionAnswer answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SelectedAnswer{" +
                "id=" + id +
                ", answer=" + answer +
                ", user=" + user +
                '}';
    }
}
