package org.testApp;

import org.testApp.api.ThemeDao;
import org.testApp.api.ThemeService;

public class ThemeServiceImpl implements ThemeService {

    private static volatile ThemeService instance;
    private ThemeDao themeDao = ThemeDaoImpl.getInstance();
    private ThemeServiceImpl() {
    }

    public static synchronized ThemeService getInstance() {
        if (instance == null) {
            instance = new ThemeServiceImpl();
        }
        return instance;
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
