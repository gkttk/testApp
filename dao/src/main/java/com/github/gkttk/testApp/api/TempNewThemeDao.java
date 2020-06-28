package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.TempNewTheme;
import java.util.List;

public interface TempNewThemeDao {
    int addTempNewTheme(TempNewTheme tempNewTheme);
    List<TempNewTheme> getAllTempNewThemes();
    TempNewTheme getTempNewTheme(int id);
    boolean updateTempNewTheme(TempNewTheme newTheme);
    List<TempNewTheme> getTempNewThemesByOwnerId(int ownerId);
    int deleteTempNewTheme(int tempNewThemeId);

}
