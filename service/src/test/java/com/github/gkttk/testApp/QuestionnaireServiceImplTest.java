package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.ThemeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.github.gkttk.testApp.api.QuestionService;
import com.github.gkttk.testApp.api.QuestionnaireDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
    private static QuestionnaireServiceImpl questionnaireService;

    @Test
    public void testAddQuestionnaireInDB() {
        Questionnaire questionnaire = new Questionnaire();
        int mockResult = 16;
        when(questionnaireDao.add(questionnaire)).thenReturn(mockResult);
        int result = questionnaireService.addQuestionnaireInDb(questionnaire);
        Assertions.assertEquals(result, mockResult);
    }

    @Test
    public void testQuestionnairesCount() {
        when(questionnaireDao.countOfQuestionnaires()).thenReturn(20L);
        int testInt = 20;
        int result = questionnaireService.questionnairesCount();
        Assertions.assertEquals(testInt, result);
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
        int userId = 1;
        int resultSize = 2;
        when(questionnaireDao.getQuestionnairesForUser(userId)).thenReturn(Arrays.asList(new Questionnaire(), new Questionnaire()));
        List<Questionnaire> listQ = questionnaireService.getQuestionnairesForStudent(userId);
        Assertions.assertEquals(resultSize, listQ.size());
    }

    @Test
    public void testQuestionnairesForUserCount() {
        int userId = 1;
        long mockResult = 10;
        when(questionnaireDao.questionnairesForUserCount(userId)).thenReturn(mockResult);
        int result = questionnaireService.questionnairesForUserCount(userId);
        Assertions.assertEquals(result, mockResult);
    }

    @Test
    public void testDateFormat() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeString = time.format(formatter);
        String result = questionnaireService.dateFormat(time);
        Assertions.assertEquals(result, timeString);
    }

    @Test
    public void testDeleteQuestionnaire() {
        int userId = 1;
        when(questionnaireDao.deleteByUserId(userId)).thenReturn(false);
        boolean result = questionnaireService.deleteQuestionnaire(userId);
        Assertions.assertFalse(result);
    }

    @Test
    public void testgetQuestionnairesForUserPagination() {
        int userId = 1;
        int numberOfPage = 2;
        int maxResultsOnPage = 3;
        int mockSize = 3;
        when(questionnaireDao.getQuestionnairesForUserPagination(userId, numberOfPage, maxResultsOnPage))
                .thenReturn(Arrays.asList(new Questionnaire(), new Questionnaire(), new Questionnaire()));
        List<Questionnaire> result = questionnaireService.getQuestionnairesForUserPagination(userId, numberOfPage, maxResultsOnPage);
        Assertions.assertAll(() -> Assertions.assertNotNull(result),
                () -> Assertions.assertEquals(mockSize, result.size()));
    }


}
