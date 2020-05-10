package org.testApp.api;

import org.testApp.Questionnaire;
import java.util.List;

public interface QuestionnaireDao {

    Integer add(Questionnaire questionnaire, double score);
    Boolean delete(Integer questionnaireId);
    List<Questionnaire> getQuestionnairesForUser(Integer userId);
    Boolean deleteByUserId(Integer userId);
    List<Questionnaire> getQuestionnaires();
    Long countOfQuestionnaires();


}
