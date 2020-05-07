package org.testApp;

public class InfoForTeacher {
    private String userLogin;
    private String userEmail;
    private String themeName;
    private Double score;

    public InfoForTeacher(String userLogin, String userEmail, String themeName, Double score) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.themeName = themeName;
        this.score = score;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getThemeName() {
        return themeName;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return  userLogin + "\t" + userEmail + "\t" + themeName + "\t" + score + "\n";
    }
}
