package org.testApp;

import org.testApp.api.AnswerDao;
import org.testApp.api.AnswerService;
import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private static volatile AnswerService instance;
    private AnswerDao answerDao = AnswerDaoImpl.getInstance();

    private AnswerServiceImpl() {}

    public static synchronized AnswerService getInstance(){
        if(instance == null){
            instance = new AnswerServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Answer> getAnswers(int questionId) {
       return answerDao.getAnswers(questionId);
    }
}
