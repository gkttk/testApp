package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Questionnaire;
import java.time.LocalDateTime;
import java.util.List;

public interface QuestionnaireService {

    Questionnaire generateQuestionnaire(int userId, int themeId);
    String getQuestionnaireThemeName(int questionnaireThemeId);
    List<Questionnaire> getQuestionnairesForStudent(int studentId);
    boolean deleteQuestionnaire(int userId);
    int questionnairesCount();
    int questionnairesForUserCount(int userId);
    int addQuestionnaireInDb(Questionnaire questionnaire);
    String dateFormat(LocalDateTime time);
    List<Questionnaire> getQuestionnairesForUserPagination(int userId, int numberOfPage, int maxResultsOnPage);
}
