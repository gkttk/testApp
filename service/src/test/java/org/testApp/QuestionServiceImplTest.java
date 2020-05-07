package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.QuestionDao;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {
    @Mock
    private static QuestionDao questionDao;

    @InjectMocks
    private static org.testApp.api.QuestionService questionService;

    @BeforeAll
    public static void createInstance() {
        questionService = QuestionServiceImpl.getInstance();
    }

    @Test
    public void testGetQuestions(){
        int themeId = 1;
        when(questionDao.getQuestions(themeId)).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, ()->questionService.getQuestions(themeId));
    }

    @Test
    public void testCheckQuestion(){
        List<Answer> answersOfTestQuestion = Arrays.asList(new Answer(1,"1","true",0),
                new Answer(2,"2","false",0),
                new Answer(3,"3","true",0),
                new Answer(4,"4","false",0));
        List<String> otherAnswers = Arrays.asList("1", "3"); //id верных ответов

        Question testQuestion = new Question(0,"testQuestion", answersOfTestQuestion, 0);

        int result = questionService.checkQuestion(testQuestion, otherAnswers);
        Assertions.assertEquals(1,result);
    }





}
