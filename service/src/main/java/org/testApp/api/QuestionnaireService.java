package org.testApp.api;

import org.testApp.Questionnaire;
import java.util.List;

public interface QuestionnaireService {

    Questionnaire generateQuestionnaire(int student_id, int theme_id);
    String getQuestionnaireThemeName(int questionnaireThemeId);
    List<Questionnaire> getQuestionnairesForStudent(int studentId);
    boolean deleteQuestionnaire(int userId);

}
