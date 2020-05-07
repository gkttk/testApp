package org.testApp;

import javax.persistence.*;
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
    @Column(name = "theme_id")
    private Integer themeId;
    @Transient
    private List<Answer> answers;

    public Question() {
    }

    public Question(int id, String questionText, List<Answer> answers, Integer themeId) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.themeId = themeId;
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

    public Integer getThemeId() {
        return themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                themeId == question.themeId &&
                questionText.equals(question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, themeId);
    }

    @Override
    public String toString() {
        return id + " " + questionText + "\n";
    }
}
