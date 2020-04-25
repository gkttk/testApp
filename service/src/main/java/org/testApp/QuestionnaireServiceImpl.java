package org.testApp;

import org.testApp.api.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class QuestionnaireServiceImpl implements QuestionnaireService {
    private static volatile QuestionnaireService instance;

    private QuestionnaireDao questionnaireDao = QuestionnaireDaoImpl.getInstance();
    private QuestionService questionServiceImpl = QuestionServiceImpl.getInstance();
    private ThemeService themeServiceImpl = ThemeServiceImpl.getInstance();

    private final int QUESTION_COUNT = 5;


    private QuestionnaireServiceImpl() {}

    public static synchronized QuestionnaireService getInstance(){
        if(instance == null){
            instance = new QuestionnaireServiceImpl();
        }
        return instance;
    }
    @Override
    public String getQuestionnaireThemeName(int questionnaireThemeId){
        return themeServiceImpl.getThemeName(questionnaireThemeId);
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
        List<Question> questions = questionServiceImpl.getQuestions(theme_id);
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
