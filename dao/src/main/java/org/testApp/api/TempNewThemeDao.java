package org.testApp.api;

import org.testApp.TempNewTheme;

import java.util.List;

public interface TempNewThemeDao {

    Integer addTempNewTheme(TempNewTheme tempNewTheme);
    List<TempNewTheme> getAllTempNewThemes();
    TempNewTheme getTempNewTheme(int id);
    boolean updateTempNewTheme(TempNewTheme newTheme);
    List<TempNewTheme> getTempNewThemesByOwnerId(int ownerId);
    int deleteTempNewTheme(int tempNewThemeId);

}