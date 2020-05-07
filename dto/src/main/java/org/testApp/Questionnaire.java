package org.testApp;

import java.util.List;

public class Questionnaire {

    private Integer id;
    private List<Question> questions;
    private Integer studentId;
    private Integer themeId;
    private Double score = 0.0;

    public Questionnaire(List<Question> questions, Integer studentId, Integer themeId) {
        this.questions = questions;
        this.studentId = studentId;
        this.themeId = themeId;
    }

    public Questionnaire(Integer id, List<Question> questions, Integer studentId, Integer themeId) {
        this.id = id;
        this.questions = questions;
        this.studentId = studentId;
        this.themeId = themeId;
    }

    public Questionnaire(Integer id, Integer studentId, Integer themeId, Double score) {
        this.id = id;
        this.studentId = studentId;
        this.themeId = themeId;
        this.score = score;
    }


    public Integer getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "id = " + id + ", id_student = " + studentId + ", id_theme = " + themeId + ", score = " + score;
    }
}
