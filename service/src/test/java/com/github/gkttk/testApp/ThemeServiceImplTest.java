package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.ThemeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ThemeServiceImplTest {
    @Mock
    private static ThemeDao themeDao;

    @InjectMocks
    private static ThemeServiceImpl themeService;

    @Test
    public void testGetThemeName(){
        int themeId = 1;
        when(themeDao.getName(1)).thenReturn("testTheme");
        String themeName = themeService.getThemeName(themeId);
        Assertions.assertEquals("testTheme", themeName);
    }





}
