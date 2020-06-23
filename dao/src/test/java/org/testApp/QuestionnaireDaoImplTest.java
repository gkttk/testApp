package org.testApp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.QuestionDao;
import org.testApp.api.QuestionnaireDao;
import org.testApp.api.ThemeDao;
import org.testApp.api.UserDao;
import org.testApp.config.DaoConfig;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class QuestionnaireDaoImplTest {

    @Autowired
    private  QuestionnaireDao questionnaireDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ThemeDao themeDao;
    @Autowired
    private QuestionDao questionDao;


    @Test
    public void testAddQuestionnaireHibernate() {
        User userFromDB = userDao.getUserByLoginHibernate("Kirill");
        Theme themeFromDB = themeDao.getTheme(2);
        double scoreForDB = 5.0;
        List<Question> questionsForDB = questionDao.getQuestions(2);
        Questionnaire questionnaire = new Questionnaire(null, scoreForDB, userFromDB, themeFromDB);
        questionsForDB.forEach(question -> question.getQuestionQuestionnaires().add(questionnaire));
        questionnaire.setQuestionnaireQuestions(questionsForDB);
        questionnaire.setDate(LocalDateTime.now());
        Integer questionnaireId = questionnaireDao.add(questionnaire);
        Assertions.assertTrue(questionnaireId > 0);
    }

    @Test
    public void testDeleteQuestionnaireHibernate() {
        User userFromDB = userDao.getUserByLoginHibernate("Kirill");
        Theme themeFromDB = themeDao.getTheme(2);
        Double scoreForDB = 5.0;
        Questionnaire questionnaire = new Questionnaire(null, scoreForDB, userFromDB, themeFromDB);
        Integer questionnaireId = questionnaireDao.add(questionnaire);
        Boolean result = questionnaireDao.delete(questionnaireId);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void testGetQuestionnairesForUserHibernate() {
        Integer userId = 1;
        List<Questionnaire> questionnairesFromDB = questionnaireDao.getQuestionnairesForUser(userId);
        Assertions.assertNotNull(questionnairesFromDB);
        questionnairesFromDB.forEach(questionnaire -> Assertions.assertEquals(userId, questionnaire.getQuestionnaireUser().getId()));
    }

    @Test
    public void testDeleteQuestionnaireByUserId() {
        User userForDB = new User(null, "testLogin", "testPassword", "testEmail");
        Integer userId = userDao.addHibernate(userForDB);
        userForDB.setId(userId);
        Theme themeFromDB = themeDao.getTheme(2);
        double scoreForDB = 5.0;
        Questionnaire questionnaire = new Questionnaire(null, scoreForDB, userForDB, themeFromDB);
        questionnaireDao.add(questionnaire);
        Boolean result = questionnaireDao.deleteByUserId(userId);
        userDao.deleteUserHibernate(userForDB.getLogin());
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetQuestionnaires() {
        List<Questionnaire> questionnairesFromDB = questionnaireDao.getQuestionnaires();
        Assertions.assertNotNull(questionnairesFromDB);
    }

    @Test
    public void testCountOfQuestionnaires() {
        Long result = questionnaireDao.countOfQuestionnaires();
        Assertions.assertTrue(result >= 0);
    }

   /* @AfterAll
    public static void trimToSize() {
        Long rows = questionnaireDao.countOfQuestionnaires();
        AutoIncrementCompressor.compressionTable("questionnaire", rows);
    }*/


  /*  @Test
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
        testQuestionnaireList.forEach((questionnaire -> Assertions.assertEquals(studentId, questionnaire.getStudentId())));
    }

    @AfterAll
    public static void trimToSize(){
        int rows = questionnaireDao.countOfQuestionnaires();
        AutoIncrementCompressor.compressionTable("questionnaire", rows);
    }

*/  //JDBC

}
