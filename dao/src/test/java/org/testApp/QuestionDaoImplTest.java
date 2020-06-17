package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.QuestionDao;
import org.testApp.config.DaoConfig;
import org.testApp.repositories.QuestionRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionRepository questionDaoSD;

    @Test
    public void testGetQuestionsHibernate() {
        int themeId = 2;
        List<Question> questionsFromDb = questionDao.getQuestions(themeId);
        questionsFromDb.forEach(question -> {
            Assertions.assertEquals(2, question.getqTheme().getId());
            System.out.println(question.getQuestionText());
        });
    }

    @Test
    public void testGetQuestionsSpringData(){
        int themeId = 2;
        List<Question> questionsFromDb = questionDaoSD.findAllByQTheme(themeId);
        questionsFromDb.forEach(question -> {
            Assertions.assertEquals(2, question.getqTheme().getId());
            System.out.println(question.getQuestionText());
        });
    }



}
