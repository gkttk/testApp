package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.QuestionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.config.DaoConfig;
import com.github.gkttk.testApp.repositories.QuestionRepository;
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
    public void testGetQuestions() {
        int themeId = 2;
        List<Question> result = questionDao.getQuestions(themeId);
        Assertions.assertNotNull(result);
        result.forEach(question -> {
            Assertions.assertEquals(2, question.getqTheme().getId());
        });
    }

    @Test
    public void testGetQuestionsSpringData(){
        int themeId = 2;
        List<Question> result = questionDaoSD.findAllByQTheme(themeId);
        Assertions.assertNotNull(result);
        result.forEach(question -> {
            Assertions.assertEquals(2, question.getqTheme().getId());
        });
    }



}
