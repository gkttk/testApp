package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.ThemeDao;
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
public class ThemeServiceImplTest {

    @Mock
    private static ThemeDao themeDao;

    @InjectMocks
    private static ThemeServiceImpl themeService;

    @Test
    public void testGetThemeName() {
        int themeId = 1;
        when(themeDao.getName(1)).thenReturn("testTheme");
        String themeName = themeService.getThemeName(themeId);
        Assertions.assertEquals("testTheme", themeName);
    }

    @Test
    public void testAddNewTheme() {
        Theme theme = new Theme();
        int mockResult = 15;
        when(themeDao.saveTheme(theme)).thenReturn(15);
        int result = themeService.addNewTheme(theme);
        Assertions.assertEquals(mockResult, result);
    }

    @Test
    public void testGetTheme() {
        int themeId = 1;
        int mockThemeId = 1;
        String mockThemeName = "testTheme";
        int mockThemeOwnerId = 1;
        when(themeDao.getTheme(themeId)).thenReturn(new Theme(1, "testTheme", 1));
        Theme result = themeService.getTheme(themeId);
        Assertions.assertAll(() -> Assertions.assertEquals(result.getId(), mockThemeId),
                () -> Assertions.assertEquals(result.getName(), mockThemeName),
                () -> Assertions.assertEquals(result.getOwnerId(), mockThemeOwnerId));
    }

    @Test
    public void testGetAllThemes() {
        int mockSize = 4;
        when(themeDao.getAllThemes()).thenReturn(Arrays.asList(new Theme(), new Theme(), new Theme(), new Theme()));
        List<Theme> result = themeService.getAllThemes();
        Assertions.assertEquals(mockSize, result.size());
    }

}
