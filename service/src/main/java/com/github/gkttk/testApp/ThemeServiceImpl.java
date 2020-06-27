package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.ThemeDao;
import com.github.gkttk.testApp.api.ThemeService;
import java.util.List;

public class ThemeServiceImpl implements ThemeService {


    private ThemeDao themeDao;

    public ThemeServiceImpl(ThemeDao themeDao) {
        this.themeDao = themeDao;
    }

    @Override
    public int addNewTheme(Theme newTheme){
       return themeDao.saveTheme(newTheme);
    }

    @Override
    public Theme getTheme(int themeId) {
        return themeDao.getTheme(themeId);
    }

    @Override
    public String getThemeName(int themeId) {
       return themeDao.getName(themeId);
    }

    @Override
    public List<Theme> getAllThemes(){
        return themeDao.getAllThemes();
    }
}
