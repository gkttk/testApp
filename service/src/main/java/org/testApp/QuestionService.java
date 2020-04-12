package org.testApp;

import org.testApp.api.IAnswerService;
import org.testApp.api.IQuestionDao;
import org.testApp.api.IQuestionService;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuestionService implements IQuestionService {
    private static volatile IQuestionService instance;
    private IQuestionDao questionDao = QuestionDao.getInstance();
    private IAnswerService answerService = AnswerService.getInstance();

    private QuestionService() {}

    public static synchronized IQuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }

    @Override
    public List<Question> getQuestions(int theme_id) {
        int question_id = 0;
        List<Answer> answers = null;
        List<Question> questions = questionDao.getQuestions(theme_id);
        for (Question question : questions) {
            question_id = question.getId();
            answers = answerService.getAnswers(question_id);
            question.setAnswers(answers);
        }
        return questions;
    }

    @Override
    public int checkQuestion(Question question, List<String> answers) {
        List<String> correctAnswers = new LinkedList<>();
        for(Answer answer: question.getAnswers()){
            if(answer.isCorrectness()){
                correctAnswers.add(String.valueOf(answer.getId()));
            }
        }
        Collections.sort(correctAnswers);
        Collections.sort(answers);
        if(correctAnswers.equals(answers)){
            return 1;
        }
        return 0;
    }
}
