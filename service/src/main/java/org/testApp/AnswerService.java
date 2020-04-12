package org.testApp;

import org.testApp.api.IAnswerDao;
import org.testApp.api.IAnswerService;
import java.util.List;

public class AnswerService implements IAnswerService {

    private static volatile IAnswerService instance;
    private IAnswerDao answerDao = AnswerDao.getInstance();

    private AnswerService() {}

    public static synchronized IAnswerService getInstance(){
        if(instance == null){
            instance = new AnswerService();
        }
        return instance;
    }

    @Override
    public List<Answer> getAnswers(int question_id) {
       return answerDao.getAnswers(question_id);
    }
}
