package org.testApp.api;

import org.testApp.Questionnaire;
import java.util.List;

public interface QuestionnaireService {

    Questionnaire generateQuestionnaire(int userId, int themeId);
    String getQuestionnaireThemeName(int questionnaireThemeId);
    List<Questionnaire> getQuestionnairesForStudent(int studentId);
    boolean deleteQuestionnaire(int userId);

}
