package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.AnswerDao;
import java.util.List;

public class AnswerDaoImplTest {
    private static AnswerDao answerDao;

    @BeforeAll
    public static void createInstance() { answerDao = AnswerDaoImpl.getInstance();}


    @Test
    public void testGetAnswersHibernate(){
        int questionId = 3;
        List<Answer> answersFromDb = answerDao.getAnswers(questionId);
        Assertions.assertNotNull(answersFromDb);
        Assertions.assertTrue(answersFromDb.size() > 1);
    }


   /* @Test
    public void testGetAnswers() {
        int question_id = 3;
        List<Answer> answersList = answerDao.getAnswers(question_id);
        Assertions.assertNotNull(answersList);
        Assertions.assertTrue(answersList.size() > 1);
    }*/  //JDBC getAnswers()

}
