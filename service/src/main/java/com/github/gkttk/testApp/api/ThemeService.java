package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Theme;
import java.util.List;

public interface ThemeService {
    Theme getTheme(int themeId);
    String getThemeName(int themeId);
    int addNewTheme(Theme newTheme);
    List<Theme> getAllThemes();
}
