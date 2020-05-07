package org.testApp;

public class Answer {
    private Integer id;
    private String answerText;
    private Boolean correctness;
    private Integer questionId;

    public Answer(int id, String answerText, boolean correctness, Integer questionId) {
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

    public Boolean isCorrectness() {
        return correctness;
    }

    public Integer getQuestionId() {
        return questionId;
    }


    @Override
    public String toString() {
        return id + " " +  answerText + " " + correctness;
    }
}
