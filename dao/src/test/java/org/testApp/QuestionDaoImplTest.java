package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.QuestionDao;
import java.util.List;

public class QuestionDaoImplTest {

    private static QuestionDao questionDao;

    @BeforeAll
    public static void createInstance(){
        questionDao = QuestionDaoImpl.getInstance();
    }

    @Test
    public void testGetQuestionsHibernate(){
        int themeId = 2;
        List<Question> questionsFromDb = questionDao.getQuestions(themeId);
        questionsFromDb.forEach(question -> {
            Assertions.assertEquals(2, question.getThemeId());
            System.out.println(question.getQuestionText());
        });
    }


   /* @Test
    public void testGetQuestions(){
        int theme_id = 1;
        List<Question> testQuestions;
        testQuestions = questionDao.getQuestions(theme_id);
        testQuestions.forEach(question -> Assertions.assertEquals(1, question.getThemeId()));
    }*/  //JDBC getQuestions

}
