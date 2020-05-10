package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.QuestionService;
import org.testApp.api.QuestionnaireDao;
import org.testApp.api.ThemeService;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireServiceImplTest {
    @Mock
    private static QuestionnaireDao questionnaireDao;
    @Mock
    private static ThemeService themeService;
    @Mock
    private static QuestionService questionService;
    @InjectMocks
    private static org.testApp.api.QuestionnaireService questionnaireService;

    @BeforeAll
    public static void createInstance() {
        questionnaireService = QuestionnaireServiceImpl.getInstance();
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
        when(questionnaireDao.getQuestionnairesForUser(studentId)).thenReturn(null);
        List<Questionnaire> listQ = questionnaireService.getQuestionnairesForStudent(studentId);
        Assertions.assertNull(listQ);
    }




}
