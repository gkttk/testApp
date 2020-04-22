package org.testApp;

import org.testApp.api.IThemeDao;
import org.testApp.api.IThemeService;

public class ThemeService implements IThemeService {

    private static volatile IThemeService instance;
    private IThemeDao themeDao = ThemeDao.getInstance();
    private ThemeService() {
    }

    public static synchronized IThemeService getInstance() {
        if (instance == null) {
            instance = new ThemeService();
        }
        return instance;
    }


    @Override
    public String getThemeName(int themeId) {
       return themeDao.getName(themeId);
    }
}
