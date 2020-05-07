package org.testApp;

import java.util.List;
import java.util.Objects;

public class Question {
    private Integer id;
    private String questionText;
    private Integer themeId;

    private List<Answer> answers;

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
