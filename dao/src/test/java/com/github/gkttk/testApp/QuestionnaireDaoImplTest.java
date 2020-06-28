package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.QuestionDao;
import com.github.gkttk.testApp.api.ThemeDao;
import com.github.gkttk.testApp.api.UserDao;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.api.QuestionnaireDao;
import com.github.gkttk.testApp.config.DaoConfig;
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
    public void testQuestionnairesForUserCount() {
        int userId = 1;
        long result = questionnaireDao.questionnairesForUserCount(userId);
        Assertions.assertTrue(result >= 0);
    }

    @Test
    public void testGetQuestionnairesForUserPagination(){
        int userId = 1;
        int numberOfPage = 1;
        int maxResultOnPage = 2;
        List<Questionnaire> result = questionnaireDao.getQuestionnairesForUserPagination(userId, numberOfPage, maxResultOnPage);
        Assertions.assertNotNull(result);
        result.forEach(questionnaire -> Assertions.assertEquals(userId, questionnaire.getQuestionnaireUser().getId()));
    }

    @Test
    public void testAddQuestionnaire() {
        User userFromDB = userDao.getUserByLogin("Kirill");
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
    public void testDeleteQuestionnaire() {
        User userFromDB = userDao.getUserByLogin("Kirill");
        Theme themeFromDB = themeDao.getTheme(2);
        double scoreForDB = 5.0;
        Questionnaire questionnaire = new Questionnaire(null, scoreForDB, userFromDB, themeFromDB);
        int questionnaireId = questionnaireDao.add(questionnaire);
        boolean result = questionnaireDao.delete(questionnaireId);
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetQuestionnairesForUser() {
        Integer userId = 1;
        List<Questionnaire> result = questionnaireDao.getQuestionnairesForUser(userId);
        Assertions.assertNotNull(result);
        result.forEach(questionnaire -> Assertions.assertEquals(userId, questionnaire.getQuestionnaireUser().getId()));
    }

    @Test
    public void testDeleteQuestionnaireByUserId() {
        User userForDB = new User(null, "testLogin", "testPassword", "testEmail");
        int userId = userDao.addUser(userForDB);
        userForDB.setId(userId);
        Theme themeFromDB = themeDao.getTheme(2);
        double scoreForDB = 5.0;
        Questionnaire questionnaire = new Questionnaire(null, scoreForDB, userForDB, themeFromDB);
        questionnaireDao.add(questionnaire);
        boolean result = questionnaireDao.deleteByUserId(userId);
        userDao.deleteUser(userForDB.getLogin());
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetQuestionnaires() {
        List<Questionnaire> result = questionnaireDao.getQuestionnaires();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testCountOfQuestionnaires() {
        long result = questionnaireDao.countOfQuestionnaires();
        Assertions.assertTrue(result >= 0);
    }


}
