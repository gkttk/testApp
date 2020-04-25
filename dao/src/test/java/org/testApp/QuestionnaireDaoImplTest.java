package org.testApp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.ConnectUtils.AutoIncrementCompressor;
import org.testApp.api.QuestionnaireDao;
import org.testApp.filters.QuestionnaireFilter;
import java.util.List;

public class QuestionnaireDaoImplTest {
    private static QuestionnaireDao questionnaireDao;

    @BeforeAll
    public static void createInstance(){
        questionnaireDao = QuestionnaireDaoImpl.getInstance();
    }

    @Test
    public void testAddQuestionnaire(){
        Questionnaire testQuestionnaire = new Questionnaire(null,1,1);
       double score = 0.5;
        int questionnaire_id = questionnaireDao.add(testQuestionnaire, score);
        if(questionnaire_id != -1){
            questionnaireDao.delete(questionnaire_id);
        }
        Assertions.assertNotEquals(-1, questionnaire_id);
    }

    @Test
    public void testDeleteQuestionnaire(){
        Questionnaire testQuestionnaire = new Questionnaire(null,1,1);
        double score = 0.5;
        int testQuestionnaireId = questionnaireDao.add(testQuestionnaire, score);
        boolean result = questionnaireDao.delete(testQuestionnaireId);
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetQuestionnaires(){
        QuestionnaireFilter questionnaireFilter = null;
        List<Questionnaire> testQuestionnaireList = questionnaireDao.getQuestionnaires(questionnaireFilter);
        Assertions.assertNotEquals(null, testQuestionnaireList);
    }

    @Test
    public void testQuestionnairesForStudent(){
      int studentId = 1;
        List<Questionnaire> testQuestionnaireList = questionnaireDao.getQuestionnairesForStudent(studentId);
        Assertions.assertNotNull(testQuestionnaireList);
        testQuestionnaireList.forEach((questionnaire -> Assertions.assertEquals(studentId, questionnaire.getId_student())));
    }

    @AfterAll
    public static void trimToSize(){
        int rows = questionnaireDao.countOfQuestionnaires();
        AutoIncrementCompressor.compressionTable("questionnaire", rows);
    }



}
