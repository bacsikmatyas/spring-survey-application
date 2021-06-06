package hu.unideb.inf.survey.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "SelectedAnswer")
public class SelectedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private QuestionAnswer answer;

    @ManyToOne
    private User user;
}
