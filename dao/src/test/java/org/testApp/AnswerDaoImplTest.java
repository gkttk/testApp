package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.AnswerDao;
import org.testApp.config.DaoConfig;

import java.util.List;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class AnswerDaoImplTest {
    @Autowired
    private AnswerDao answerDao;


    @Test
    public void testGetAnswersHibernate() {
        int questionId = 3;
        List<Answer> answersFromDb = answerDao.getAnswers(questionId);
        Assertions.assertNotNull(answersFromDb);
        Assertions.assertTrue(answersFromDb.size() > 1);
    }


}
