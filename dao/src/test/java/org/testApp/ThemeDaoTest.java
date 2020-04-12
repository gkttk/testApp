package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.IThemeDao;

public class ThemeDaoTest {
    private static IThemeDao themeDao;

    @BeforeAll
    public static void createInstance(){
        themeDao = ThemeDao.getInstance();
    }

    @Test
    public void testGetThemeNameByThemeId(){
        int themeId = 2;
        String themeName = themeDao.getThemeNameByThemeId(themeId);
        Assertions.assertEquals("Наследование", themeName);
    }

}
