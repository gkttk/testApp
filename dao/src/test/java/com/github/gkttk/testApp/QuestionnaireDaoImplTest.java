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
    public void testCountQuestionnairesForUser() {
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


}
