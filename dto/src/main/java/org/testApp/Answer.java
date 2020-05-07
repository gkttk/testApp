package org.testApp;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text")
    private String answerText;
    @Column(name = "correctness")
    private String correctness;
    @Column(name = "question_id")
    private Integer questionId;


    public Answer() {
    }

    public Answer(int id, String answerText, String correctness, Integer questionId) {
        this.id = id;
        this.answerText = answerText;
        this.correctness = correctness;
        this.questionId = questionId;
    }




    public Integer getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getCorrectness() {
        return correctness;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setCorrectness(String correctness) {
        this.correctness = correctness;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return id + " " + answerText + " " + correctness;
    }
}
