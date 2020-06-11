package org.testApp.api;


import org.testApp.TempNewTheme;

import java.util.List;

public interface TempNewThemeService {
   int addTempNewThemeForPermit(int ownerId, String themeName, int numberOfQuestions);
   List<TempNewTheme> getTempNewThemesForUser(int ownerId);
   List<TempNewTheme> getAllTempNewThemes();
   boolean acceptTempNewTheme(int tempNewThemeId);
   int refuseTempNewTheme(int tempNewThemeId);
   TempNewTheme getTempNewTheme(int tempNewThemeId);

   }
