package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.AnswerDao;
import com.github.gkttk.testApp.api.AnswerService;
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
