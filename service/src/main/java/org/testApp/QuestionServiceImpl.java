package org.testApp;

import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.AnswerService;
import org.testApp.api.QuestionDao;
import org.testApp.api.QuestionService;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;
    private AnswerService answerService;

    public QuestionServiceImpl(QuestionDao questionDao, AnswerService answerService) {
        this.questionDao = questionDao;
        this.answerService = answerService;
    }

    @Override
    @Transactional
    public List<Question> getQuestions(int themeId) {
        int questionId = 0;
        List<Answer> answers = null;
        List<Question> questions = questionDao.getQuestions(themeId);
        for (Question question : questions) {
            questionId = question.getId();
            answers = answerService.getAnswers(questionId);
            question.setAnswers(answers);
        }
        return questions;
    }

    @Override
    @Transactional
    public int checkQuestion(Question question, List<String> answers) {
        List<String> correctAnswers = new LinkedList<>();
        for(Answer answer: question.getAnswers()){
            if(answer.getCorrectness().equalsIgnoreCase("true")){
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
