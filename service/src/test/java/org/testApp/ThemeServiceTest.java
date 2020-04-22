package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.IThemeDao;
import org.testApp.api.IThemeService;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ThemeServiceTest {
    @Mock
    private static IThemeDao themeDao;

    @InjectMocks
    private static IThemeService themeService;

    @BeforeAll
    public static void createInstance() {
        themeService = ThemeService.getInstance();
    }


    @Test
    public void testGetThemeName(){
        int themeId = 1;
        when(themeDao.getName(1)).thenReturn("testTheme");
        String themeName = themeService.getThemeName(themeId);
        Assertions.assertEquals("testTheme", themeName);
    }





}
