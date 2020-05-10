package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.ThemeDao;
import org.testApp.api.ThemeService;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ThemeServiceImplTest {
    @Mock
    private static ThemeDao themeDao;

    @InjectMocks
    private static ThemeService themeService;

    @BeforeAll
    public static void createInstance() {
        themeService = ThemeServiceImpl.getInstance();
    }

    @Test
    public void testGetThemeName(){
        int themeId = 1;
        when(themeDao.getName(1)).thenReturn("testTheme");
        String themeName = themeService.getThemeName(themeId);
        Assertions.assertEquals("testTheme", themeName);
    }





}
