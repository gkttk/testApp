package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.*;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireServiceTest {
    @Mock
    private static IQuestionnaireDao questionnaireDao;
    @Mock
    private static IThemeService themeService;
    @Mock
    private static IQuestionService questionService;
    @InjectMocks
    private static IQuestionnaireService questionnaireService;

    @BeforeAll
    public static void createInstance() {
        questionnaireService = QuestionnaireService.getInstance();
    }

    @Test
    public void testGetQuestionnaireThemeName() {
        when(themeService.getThemeName(1)).thenReturn("testString");
        String result = questionnaireService.getQuestionnaireThemeName(1);
        Assertions.assertEquals("testString", result);
    }

    @Test
    public void testGenerateQuestionnaire() {
        when(questionService.getQuestions(1)).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> questionnaireService.generateQuestionnaire(1, 1));
    }

    @Test
    public void testGetQuestionnairesForStudent() {
        int studentId = 1;
        when(questionnaireDao.getQuestionnairesForStudent(studentId)).thenReturn(null);
        List<Questionnaire> listQ = questionnaireService.getQuestionnairesForStudent(studentId);
        Assertions.assertNull(listQ);
    }




}
