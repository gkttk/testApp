package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.IAnswerDao;
import java.util.List;

public class AnswerDaoTest {
    private static IAnswerDao answerDao;

    @BeforeAll
    public static void createInstance() { answerDao = AnswerDao.getInstance();}

    @Test
    public void testGetAnswers() {
        int question_id = 3;
        List<Answer> answersList = answerDao.getAnswers(question_id);
        Assertions.assertNotNull(answersList);
        Assertions.assertTrue(answersList.size() > 1);
    }

}
