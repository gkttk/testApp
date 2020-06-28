package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Questionnaire;
import java.util.List;

public interface QuestionnaireDao {
    int add(Questionnaire questionnaire);
    boolean delete(Integer questionnaireId);
    List<Questionnaire> getQuestionnairesForUser(Integer userId);
    boolean deleteByUserId(Integer userId);
    List<Questionnaire> getQuestionnaires();
    Long countOfQuestionnaires();
    List<Questionnaire> getQuestionnairesForUserPagination(int userId, int numberOfPage, int maxResultsOnPage);
    Long questionnairesForUserCount(int userId);

}
