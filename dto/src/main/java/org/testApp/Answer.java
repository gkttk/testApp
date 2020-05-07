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

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question aQuestion;

    public Answer() {
    }

    public Answer(int id, String answerText, String correctness, Question aQuestion) {
        this.id = id;
        this.answerText = answerText;
        this.correctness = correctness;
        this.aQuestion = aQuestion;
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


    public Question getaQuestion() {
        return aQuestion;
    }

    public void setaQuestion(Question aQuestion) {
        this.aQuestion = aQuestion;
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


    @Override
    public String toString() {
        return id + " " + answerText + " " + correctness;
    }
}
