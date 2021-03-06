package com.github.gkttk.testApp;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionnaire")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User questionnaireUser;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theme_id")
    private Theme questionnaireTheme;

    @Column(name = "score")
    private Double score = 0.0;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "questions_questionnaires", joinColumns = {@JoinColumn(name = "questionnaire_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Question> questionnaireQuestions = new ArrayList<>();

    @Column(name = "date")
    private LocalDateTime date;



    public Questionnaire() {
    }

    public Questionnaire(Integer id, User questionnaireUser, Theme questionnaireTheme, List<Question> questionnaireQuestions) {
        this.id = id;
        this.questionnaireUser = questionnaireUser;
        this.questionnaireTheme = questionnaireTheme;
        this.questionnaireQuestions = questionnaireQuestions;
    }

    public Questionnaire(Integer id, Double score, User questionnaireUser, Theme questionnaireTheme) {
        this.id = id;
        this.score = score;
        this.questionnaireUser = questionnaireUser;
        this.questionnaireTheme = questionnaireTheme;
    }

    public Questionnaire(Integer id, Double score, User questionnaireUser, Theme questionnaireTheme, List<Question> questionnaireQuestions) {
        this.id = id;
        this.score = score;
        this.questionnaireUser = questionnaireUser;
        this.questionnaireTheme = questionnaireTheme;
        this.questionnaireQuestions = questionnaireQuestions;
    }


    public Integer getId() {
        return id;
    }

    public List<Question> getQuestionnaireQuestions() {
        return questionnaireQuestions;
    }

    public void setQuestionnaireQuestions(List<Question> questionnaireQuestions) {
        this.questionnaireQuestions = questionnaireQuestions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public User getQuestionnaireUser() {
        return questionnaireUser;
    }

    public void setQuestionnaireUser(User questionnaireUser) {
        this.questionnaireUser = questionnaireUser;
    }

    public Theme getQuestionnaireTheme() {
        return questionnaireTheme;
    }

    public void setQuestionnaireTheme(Theme questionnaireTheme) {
        this.questionnaireTheme = questionnaireTheme;
    }

    public Double getScore() {
        return score;
    }


}
