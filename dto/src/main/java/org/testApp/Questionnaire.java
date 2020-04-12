package org.testApp;

import java.util.List;

public class Questionnaire {

    private int id;
    private List<Question> questions;
    private int id_student;
    private int id_theme;
    private double score = 0.0;

    public Questionnaire(List<Question> questions, int id_student, int id_theme) {
        this.questions = questions;
        this.id_student = id_student;
        this.id_theme = id_theme;
    }

    public Questionnaire(int id, List<Question> questions, int id_student, int id_theme) {
        this.id = id;
        this.questions = questions;
        this.id_student = id_student;
        this.id_theme = id_theme;
    }

    public Questionnaire(int id, int id_student, int id_theme, double score) {
        this.id = id;
        this.id_student = id_student;
        this.id_theme = id_theme;
        this.score = score;
    }


    public int getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getId_student() {
        return id_student;
    }

    public int getId_theme() {
        return id_theme;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "id = " + id + ", id_student = " + id_student + ", id_theme = " + id_theme + ", score = " + score;
    }
}
