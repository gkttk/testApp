package org.testApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionnaireServiceImpl implements QuestionnaireService {

    private QuestionnaireDao questionnaireDao;
    private QuestionService questionServiceImpl;
    private ThemeService themeServiceImpl;
    private UserService userServiceImpl;

    @Value("5")
    private final int QUESTION_COUNT = 5;


    public QuestionnaireServiceImpl(QuestionnaireDao questionnaireDao,
                                    QuestionService questionServiceImpl,
                                    ThemeService themeServiceImpl,
                                    UserService userServiceImpl) {
        this.questionnaireDao = questionnaireDao;
        this.questionServiceImpl = questionServiceImpl;
        this.themeServiceImpl = themeServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }


    @Override
    @Transactional
    public String getQuestionnaireThemeName(int questionnaireThemeId) {
        return themeServiceImpl.getThemeName(questionnaireThemeId);
    }

    @Override
    @Transactional
    public boolean deleteQuestionnaire(int userId) {
        return questionnaireDao.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public Questionnaire generateQuestionnaire(int userId, int themeId) {
        Random random = new Random();
        int randomNum;
        Question question = null;
        List<Question> questions = questionServiceImpl.getQuestions(themeId);
        List<Question> questionForQuestionnaire = new LinkedList<>();
        while (questionForQuestionnaire.size() < QUESTION_COUNT) {
            randomNum = random.nextInt(questions.size());
            question = questions.get(randomNum);
            if (!questionForQuestionnaire.contains(question)) {
                questionForQuestionnaire.add(question);
            }
        }
        Theme theme = themeServiceImpl.getTheme(themeId);
        User user = userServiceImpl.getUser(userId);
        return new Questionnaire(null, user, theme, questionForQuestionnaire);
    }

    public List<Questionnaire> getQuestionnairesForStudent(int userId) {
        return questionnaireDao.getQuestionnairesForUser(userId);
    }


}
