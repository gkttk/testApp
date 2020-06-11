package org.testApp;

import org.testApp.api.TempNewThemeDao;
import org.testApp.api.TempNewThemeService;

import java.util.List;

public class TempNewThemeServiceImpl implements TempNewThemeService {

    private TempNewThemeDao tempNewThemeDao;

    public TempNewThemeServiceImpl(TempNewThemeDao tempNewThemeDao) {
        this.tempNewThemeDao = tempNewThemeDao;
    }

    @Override
    public int addTempNewThemeForPermit(int ownerId, String themeName, int numberOfQuestions) {
        TempNewTheme tempNewTheme = new TempNewTheme(ownerId, themeName, numberOfQuestions);
        return tempNewThemeDao.addTempNewTheme(tempNewTheme);
    }

    @Override
    public List<TempNewTheme> getTempNewThemesForUser(int ownerId) {
        return tempNewThemeDao.getTempNewThemesByOwnerId(ownerId);
    }

    @Override
    public List<TempNewTheme> getAllTempNewThemes(){
        return tempNewThemeDao.getAllTempNewThemes();
    }

    @Override
    public boolean acceptTempNewTheme(int tempNewThemeId){
        TempNewTheme tempNewTheme = tempNewThemeDao.getTempNewTheme(tempNewThemeId);
        tempNewTheme.setPermit(true);
        return tempNewThemeDao.updateTempNewTheme(tempNewTheme);
    }

    @Override
    public int refuseTempNewTheme(int tempNewThemeId) {
        return tempNewThemeDao.deleteTempNewTheme(tempNewThemeId);
    }

    @Override
    public TempNewTheme getTempNewTheme(int tempNewThemeId) {
        return tempNewThemeDao.getTempNewTheme(tempNewThemeId);
    }

}
