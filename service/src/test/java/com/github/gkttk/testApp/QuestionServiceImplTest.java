package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.QuestionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private static QuestionDao questionDao;

    @InjectMocks
    private static QuestionServiceImpl questionService;

    @Test
    public void testGetQuestions() {
        int themeId = 1;
        when(questionDao.getQuestions(themeId)).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> questionService.getQuestions(themeId));
    }

    @Test
    public void testCheckQuestion() {
        List<Answer> answersOfTestQuestion = Arrays.asList(new Answer(1, "1", "true", null),
                new Answer(2, "2", "false", null),
                new Answer(3, "3", "true", null),
                new Answer(4, "4", "false", null));
        List<String> otherAnswers = Arrays.asList("1", "3"); //id верных ответов
        Question testQuestion = new Question(0, "testQuestion", answersOfTestQuestion, null);
        int result = questionService.checkQuestion(testQuestion, otherAnswers);
        Assertions.assertEquals(1, result);
    }


}
