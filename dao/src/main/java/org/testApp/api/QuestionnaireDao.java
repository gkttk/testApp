package org.testApp.api;

import org.testApp.Questionnaire;
import java.util.List;

public interface QuestionnaireDao {

    Integer add(Questionnaire questionnaire);
    Boolean delete(Integer questionnaireId);
    List<Questionnaire> getQuestionnairesForUser(Integer userId);
    Boolean deleteByUserId(Integer userId);
    List<Questionnaire> getQuestionnaires();
    Long countOfQuestionnaires();
    List<Questionnaire> getQuestionnairesForUserPagination(int userId, int numberOfPage, int maxResultsOnPage);
    Long questionnairesForUserCount(int userId);

}
