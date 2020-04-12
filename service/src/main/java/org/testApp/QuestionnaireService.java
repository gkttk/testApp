package org.testApp;

import org.testApp.api.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionnaireService implements IQuestionnaireService {
    private static volatile IQuestionnaireService instance;

    private IQuestionnaireDao questionnaireDao = QuestionnaireDao.getInstance();
    private IQuestionService questionService = QuestionService.getInstance();
    private IThemeService themeService = ThemeService.getInstance();

    private final int QUESTION_COUNT = 5;


    private QuestionnaireService() {}

    public static synchronized IQuestionnaireService getInstance(){
        if(instance == null){
            instance = new QuestionnaireService();
        }
        return instance;
    }
    @Override
    public String getQuestionnaireThemeName(int questionnaireThemeId){
        return themeService.getThemeName(questionnaireThemeId);
    }
    @Override
    public boolean deleteQuestionnaire(int userId){
       return questionnaireDao.deleteByUserId(userId);
    }

    @Override
    public Questionnaire generateQuestionnaire(int student_id, int theme_id) {
        Random random = new Random();
        int randomNum;
        Question question =  null;
        List<Question> questions = questionService.getQuestions(theme_id);
        List<Question> questionForQuestionnaire = new LinkedList<>();
        while(questionForQuestionnaire.size() < QUESTION_COUNT){
            randomNum = random.nextInt(questions.size());
            question = questions.get(randomNum);
            if(!questionForQuestionnaire.contains(question)) {
                questionForQuestionnaire.add(question);
            }
        }
        return new Questionnaire(questionForQuestionnaire, student_id, theme_id);
    }

    public List<Questionnaire> getQuestionnairesForStudent(int studentId){
        return questionnaireDao.getQuestionnairesForStudent(studentId);

    }


}
