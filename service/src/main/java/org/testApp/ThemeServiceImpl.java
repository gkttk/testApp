package org.testApp;

import org.testApp.api.ThemeDao;
import org.testApp.api.ThemeService;

public class ThemeServiceImpl implements ThemeService {


    private ThemeDao themeDao;


    public ThemeServiceImpl(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    @Override
    public Theme getTheme(int themeId) {
        return themeDao.getTheme(themeId);
    }

    @Override
    public String getThemeName(int themeId) {
       return themeDao.getName(themeId);
    }
}
