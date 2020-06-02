package org.testApp;

import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.AnswerDao;
import org.testApp.api.AnswerService;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;

    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    public List<Answer> getAnswers(int questionId) {
       return answerDao.getAnswers(questionId);
    }
}
