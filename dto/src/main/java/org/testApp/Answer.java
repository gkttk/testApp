package org.testApp;

public class Answer {
    private int id;
    private String answerText;
    private boolean correctness;
    private int id_question;

    public Answer(int id, String answerText, boolean correctness, int id_question) {
        this.id = id;
        this.answerText = answerText;
        this.correctness = correctness;
        this.id_question = id_question;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public int getId_question() {
        return id_question;
    }


    @Override
    public String toString() {
        return id + " " +  answerText + " " + correctness;
    }
}
