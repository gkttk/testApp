package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.IQuestionDao;
import java.util.List;

public class QuestionDaoTest {

    private static IQuestionDao questionDao;

    @BeforeAll
    public static void createInstance(){
        questionDao = QuestionDao.getInstance();
    }

    @Test
    public void testGetQuestions(){
        int theme_id = 1;
        List<Question> testQuestions;
        testQuestions = questionDao.getQuestions(theme_id);
        testQuestions.forEach(question -> Assertions.assertEquals(1, question.getTheme_id()));
    }

}
