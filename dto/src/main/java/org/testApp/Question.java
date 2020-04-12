package org.testApp;

import java.util.List;
import java.util.Objects;

public class Question {
    private int id;
    private String questionText;
    private List<Answer> answers;
    private int theme_id;

    public Question(int id, String questionText, List<Answer> answers, int theme_id) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.theme_id = theme_id;
    }

    public int getId() {
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

    public int getTheme_id() {
        return theme_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                theme_id == question.theme_id &&
                questionText.equals(question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, theme_id);
    }

    @Override
    public String toString() {
        return id + " " + questionText + "\n";
    }
}
