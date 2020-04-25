package org.testApp.api;

import org.testApp.Questionnaire;
import org.testApp.filters.QuestionnaireFilter;
import java.util.List;

public interface QuestionnaireDao {
    int add(Questionnaire questionnaire, double score);
    List<Questionnaire> getQuestionnaires(QuestionnaireFilter questionnaireFilter);
    boolean delete(int questionnaire_id);
    boolean deleteByUserId(int user_id);
     int countOfQuestionnaires();
     List<Questionnaire> getQuestionnairesForStudent(int student_id);

}
