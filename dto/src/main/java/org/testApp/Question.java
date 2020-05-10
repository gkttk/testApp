package org.testApp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme qTheme;

    @OneToMany(mappedBy = "aQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;


    @ManyToMany(mappedBy = "questionnaireQuestions", fetch = FetchType.EAGER)
   @Transient
    private List<Questionnaire> questionQuestionnaires = new ArrayList<>();

    public Question() {
    }

    public Question(int id, String questionText, List<Answer> answers, Theme qTheme) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.qTheme = qTheme;
    }


    public Integer getId() {
        return id;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Theme getqTheme() {
        return qTheme;
    }

    public void setqTheme(Theme qTheme) {
        this.qTheme = qTheme;
    }

    public List<Questionnaire> getQuestionQuestionnaires() {
        return questionQuestionnaires;
    }

    public void setQuestionQuestionnaires(List<Questionnaire> questionQuestionnaires) {
        this.questionQuestionnaires = questionQuestionnaires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(questionText, question.questionText) &&
                Objects.equals(qTheme, question.qTheme) &&
                Objects.equals(answers, question.answers) &&
                Objects.equals(questionQuestionnaires, question.questionQuestionnaires);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, qTheme, answers, questionQuestionnaires);
    }

    @Override
    public String toString() {
        return id + " " + questionText + "\n";
    }
}
