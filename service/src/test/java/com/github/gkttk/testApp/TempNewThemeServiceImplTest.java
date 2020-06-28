package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.TempNewThemeDao;
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
public class TempNewThemeServiceImplTest {

    @Mock
    private static TempNewThemeDao tempNewThemeDao;

    @InjectMocks
    private static TempNewThemeServiceImpl tempNewThemeService;

    @Test
    public void testAddTempNewThemeForPermit() {
        int mockOwnerId = 1;
        String mockTempNewThemeName = "newThemeTest";
        int mockNumberOfQuestions = 10;
        int mockResult = 20;
        TempNewTheme mockTempNewTheme = new TempNewTheme(mockOwnerId, mockTempNewThemeName, mockNumberOfQuestions);
        when(tempNewThemeDao.addTempNewTheme(mockTempNewTheme)).thenReturn(mockResult);
        int result = tempNewThemeService.addTempNewThemeForPermit(mockOwnerId, mockTempNewThemeName, mockNumberOfQuestions);
        Assertions.assertEquals(result, mockResult);
    }

    @Test
    public void testGetTempNewThemesForUser() {
        int userId = 1;
        int mockSize = 2;
        when(tempNewThemeDao.getTempNewThemesByOwnerId(userId)).thenReturn(Arrays.asList(new TempNewTheme(), new TempNewTheme()));
        List<TempNewTheme> result = tempNewThemeService.getTempNewThemesForUser(userId);
        Assertions.assertEquals(result.size(), mockSize);
    }

    @Test
    public void testGetAllTempNewThemes() {
        int mockSize = 3;
        when(tempNewThemeDao.getAllTempNewThemes()).thenReturn(Arrays.asList(new TempNewTheme(), new TempNewTheme(), new TempNewTheme()));
        List<TempNewTheme> result = tempNewThemeService.getAllTempNewThemes();
        Assertions.assertEquals(result.size(), mockSize);
    }

    @Test
    public void testAcceptTempNewTheme() {
        int mockTempNewThemeId = 10;
        TempNewTheme mockTempNewTheme = new TempNewTheme(1, "newThemeTest", 1);
        when(tempNewThemeDao.getTempNewTheme(mockTempNewThemeId)).thenReturn(mockTempNewTheme);
        when(tempNewThemeDao.updateTempNewTheme(mockTempNewTheme)).thenReturn(false);
        boolean result = tempNewThemeService.acceptTempNewTheme(mockTempNewThemeId);
        Assertions.assertFalse(result);
    }

    @Test
    public void testRefuseTempNewTheme() {
        int mockTempNewThemeId = 10;
        int mockResult = 100;
        when(tempNewThemeDao.deleteTempNewTheme(mockTempNewThemeId)).thenReturn(100);
        int result = tempNewThemeService.refuseTempNewTheme(mockTempNewThemeId);
        Assertions.assertEquals(mockResult, result);
    }

    @Test
    public void testGetTempNewTheme() {
        int tempNewThemeId = 50;
        int mockOwnerId = 1;
        String mockTempNewThemeName = "newThemeTest";
        int mockNumberOfQuestions = 10;
        when(tempNewThemeDao.getTempNewTheme(tempNewThemeId))
                .thenReturn(new TempNewTheme(mockOwnerId, mockTempNewThemeName, mockNumberOfQuestions));
        TempNewTheme result = tempNewThemeService.getTempNewTheme(tempNewThemeId);
        Assertions.assertAll(() -> Assertions.assertEquals(result.getOwnerId(), mockOwnerId),
                () -> Assertions.assertEquals(result.getThemeName(), mockTempNewThemeName),
                () -> Assertions.assertEquals(result.getNumberOfQuestions(), mockNumberOfQuestions));
    }

}
