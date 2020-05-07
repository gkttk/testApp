package org.testApp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User questionnaireUser;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme questionnaireTheme;

    @Column(name = "score")
    private Double score = 0.0;


   /* @ManyToMany(mappedBy = "questionQuestionnaires", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)*/
    @Transient
    private List<Question> questionnaireQuestions = new ArrayList<>();


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
