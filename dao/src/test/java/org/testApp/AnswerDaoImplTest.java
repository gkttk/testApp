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



}
